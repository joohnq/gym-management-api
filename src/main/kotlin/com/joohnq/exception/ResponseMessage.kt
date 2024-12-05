package com.joohnq.exception

import io.ktor.http.*

sealed class ResponseMessage(
    open val text: String, open val status: HttpStatusCode
)
