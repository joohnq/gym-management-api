package com.joohnq.application.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val firstname: String,
    val lastname: String,
//    val birthday: String,
//    val cpf: String,
//    val address: String,
//    val email: String,
//    val phone: String,
//    val neighborhood: String,
//    val city: String,
//    val state: String,
    val createdAt: String,
)