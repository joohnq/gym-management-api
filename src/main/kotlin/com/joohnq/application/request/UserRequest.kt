package com.joohnq.application.request

import com.joohnq.domain.entity.User
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import java.time.LocalDateTime

@Serializable
data class UserRequest(
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
){
    companion object {
        fun UserRequest.toDomain(): User {
            return User(
                id = ObjectId(),
                firstname = firstname,
                lastname = lastname,
//                birthday = birthday,
//                cpf = cpf,
//                address = address,
//                email = email,
//                phone = phone,
//                neighborhood = neighborhood,
//                city = city,
//                state = state,
                createdAt = LocalDateTime.now().toString(),
            )
        }
    }
}

