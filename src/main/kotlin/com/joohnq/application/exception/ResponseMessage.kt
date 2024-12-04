package com.joohnq.application.exception

import io.ktor.http.*

sealed class ResponseMessage(
    open val text: String, open val status: HttpStatusCode
)
