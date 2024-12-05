package com.joohnq.application.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val token: String
)
