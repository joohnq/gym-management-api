package com.joohnq.application.mappers

import com.joohnq.application.exception.ResponseMessage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

suspend fun RoutingCall.respondText(error: ResponseMessage){
    respondText(text = error.text, status = error.status)
}

suspend inline fun <reified T : Any> ApplicationCall.receiveOrRespondBadRequest(): T? =
    try {
        receive<T>()
    } catch (e: Exception) {
        respond(HttpStatusCode.BadRequest, "Invalid request, please check your fields")
        null
    }
