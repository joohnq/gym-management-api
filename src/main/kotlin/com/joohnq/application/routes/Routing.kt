package com.joohnq.application.routes

import com.joohnq.domain.ports.UserRepository
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val repository by inject<UserRepository>()

    routing {
        userRoutes(repository)
    }
}