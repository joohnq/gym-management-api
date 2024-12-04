package com.joohnq.application.exception

import io.ktor.http.*

sealed class UserResponseSuccess(override val text: String, override val status: HttpStatusCode) :
    ResponseMessage(text = text, status = status) {
    data object UpdatedSuccess:
        UserResponseSuccess(text = "Fitness updated successfully", status = HttpStatusCode.OK)
}