package com.joohnq.application.exception

import io.ktor.http.*

sealed class UserResponseError(override val text: String, override val status: HttpStatusCode) :
    ResponseMessage(text = text, status = status) {
    data object MissingId :
        UserResponseError(text = "Missing user id", status = HttpStatusCode.BadRequest)

    data object ErrorOnUpdate :
        UserResponseError(text = "Error on update", status = HttpStatusCode.BadRequest)

    data object ErrorOnCreate :
        UserResponseError(text = "Error on create", status = HttpStatusCode.BadRequest)
}