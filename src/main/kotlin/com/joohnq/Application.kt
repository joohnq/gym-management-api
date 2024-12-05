package com.joohnq

import com.joohnq.application.AppConfig
import com.joohnq.application.configureSecurity
import com.joohnq.application.routes.configureRouting
import com.joohnq.application.secutiry.hashing.HashingService
import com.joohnq.application.secutiry.token.TokenConfig
import com.joohnq.application.secutiry.token.TokenService
import com.joohnq.di.configureKoin
import com.joohnq.domain.ports.UserRepository
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json { prettyPrint = true; isLenient = true })
    }
    configureKoin()
    val tokenConfig by inject<TokenConfig>()
    val userRepository by inject<UserRepository>()
    val hashingService by inject<HashingService>()
    val tokenService by inject<TokenService>()
    val appConfig by inject<AppConfig>()

    configureSecurity(tokenConfig = tokenConfig, appConfig = appConfig)
    configureRouting(
        userRepository = userRepository,
        hashingService = hashingService,
        tokenService = tokenService,
        tokenConfig = tokenConfig
    )
}


