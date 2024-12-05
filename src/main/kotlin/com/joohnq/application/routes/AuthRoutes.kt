package com.joohnq.application.routes

import com.joohnq.application.request.SignInRequest
import com.joohnq.application.request.SignUpRequest
import com.joohnq.application.response.TokenResponse
import com.joohnq.application.secutiry.hashing.HashingService
import com.joohnq.application.secutiry.hashing.SaltedHash
import com.joohnq.application.secutiry.token.TokenClaim
import com.joohnq.application.secutiry.token.TokenConfig
import com.joohnq.application.secutiry.token.TokenService
import com.joohnq.domain.entity.User
import com.joohnq.domain.entity.User.Companion.toResponse
import com.joohnq.domain.ports.UserRepository
import com.joohnq.exception.UserResponseError
import com.joohnq.mappers.receiveOrRespondBadRequest
import com.joohnq.mappers.respondText
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDateTime

fun Route.authRoutes(
    hashingService: HashingService,
    userRepository: UserRepository,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    post("signup") {
        val request = call.receiveOrRespondBadRequest<SignUpRequest>() ?: return@post
        val areFieldsBlack = request.email.isBlank() || request.password.isBlank() || request.name.isBlank()
        val isPasswordTooShort = request.password.length < 8
        if (areFieldsBlack || isPasswordTooShort) {
            call.respond(HttpStatusCode.Conflict, "Invalid request, please check your fields")
            return@post
        }

        val saltedHash = hashingService.generateSaltedHash(request.password)
        val user = User(
            name = request.name,
            email = request.email,
            password = saltedHash.hash,
            salt = saltedHash.salt,
            createdAt = LocalDateTime.now().toString()
        )

        val res = userRepository.create(user)

        if (!res) {
            call.respond(UserResponseError.ErrorOnCreate)
        }

        call.respond(user.toResponse())
    }

    post("signin") {
        val request = call.receiveOrRespondBadRequest<SignInRequest>() ?: return@post

        val user = userRepository.getByEmail(request.email)
            ?: let { call.respondText(UserResponseError.UserNotFound); return@post }

        val isValidPassword = hashingService.verify(
            value = request.password,
            saltedHash = SaltedHash(hash = user.password, salt = user.salt)
        )

        if (!isValidPassword) {
            call.respondText(UserResponseError.InvalidPassword); return@post
        }

        val token = tokenService.generate(
            tokenConfig,
            TokenClaim("id", user.id.toString()),
        )

        call.respond(
            status = HttpStatusCode.OK,
            message = TokenResponse(token = token)
        )
    }
    authenticate {
        get("authenticate") {
            call.respond(HttpStatusCode.OK)
        }
    }
}

fun Route.getSecretInfo() {
    authenticate {
        get("secret") {
            val principal = call.principal<JWTPrincipal>()
            val id = principal?.payload?.getClaim("id")?.asString()
            call.respondText("Hello $id")
        }
    }
}