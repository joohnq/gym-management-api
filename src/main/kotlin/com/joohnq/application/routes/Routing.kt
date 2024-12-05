package com.joohnq.application.routes

import com.joohnq.application.secutiry.hashing.HashingService
import com.joohnq.application.secutiry.token.TokenConfig
import com.joohnq.application.secutiry.token.TokenService
import com.joohnq.domain.ports.UserRepository
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting(
    hashingService: HashingService,
    userRepository: UserRepository,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    routing {
        userRoutes(userRepository)
        authRoutes(
            hashingService = hashingService,
            userRepository = userRepository,
            tokenService = tokenService,
            tokenConfig = tokenConfig
        )
        getSecretInfo()
    }
}