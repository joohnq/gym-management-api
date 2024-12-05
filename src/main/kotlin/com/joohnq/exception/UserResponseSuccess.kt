package com.joohnq.exception

import io.ktor.http.*

sealed class UserResponseSuccess(override val text: String, override val status: HttpStatusCode) :
    ResponseMessage(text = text, status = status) {
    data object UpdatedSuccess :
        UserResponseSuccess(text = "Fitness updated successfully", status = HttpStatusCode.OK)

    data object DeleteSuccess :
        UserResponseSuccess(text = "Fitness deleted successfully", status = HttpStatusCode.OK)
}