package com.joohnq.domain.entity

import com.joohnq.application.response.UserResponse
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    @BsonId val id: ObjectId,
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
) {
    companion object {
        fun User.toResponse() = UserResponse(
            id = id.toString(),
            firstname = firstname,
            lastname = lastname,
//            birthday = birthday,
//            cpf = cpf,
//            address = address,
//            email = email,
//            phone = phone,
//            neighborhood = neighborhood,
//            city = city,
//            state = state,
            createdAt = createdAt,
        )
    }
}