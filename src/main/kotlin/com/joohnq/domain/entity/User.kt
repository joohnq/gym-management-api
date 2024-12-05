package com.joohnq.domain.entity

import com.joohnq.application.response.UserResponse
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.time.LocalDateTime

data class User(
    @BsonId val id: ObjectId = ObjectId(),
    val name: String,
    val email: String,
    val password: String,
    val salt: String,
    val createdAt: String,
) {
    companion object {
        fun User.toResponse() = UserResponse(
            id = id.toString(),
            name = name,
            email = email,
            createdAt = createdAt,
        )
    }
}