package com.joohnq.exception

import io.ktor.http.*

sealed class UserResponseError(override val text: String, override val status: HttpStatusCode) :
    ResponseMessage(text = text, status = status) {
    data object MissingId :
        UserResponseError(text = "Missing user id", status = HttpStatusCode.BadRequest)

    data object ErrorOnUpdate :
        UserResponseError(text = "Error on update", status = HttpStatusCode.Conflict)

    data object ErrorOnCreate :
        UserResponseError(text = "Error on create", status = HttpStatusCode.Conflict)

    data object UserNotFound :
        UserResponseError(text = "User not found with this email", status = HttpStatusCode.Conflict)

    data object InvalidPassword :
        UserResponseError(text = "Invalid password", status = HttpStatusCode.Conflict)
}