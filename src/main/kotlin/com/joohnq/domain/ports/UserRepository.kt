package com.joohnq.domain.ports

import com.joohnq.application.request.UserRequest
import com.joohnq.domain.entity.User
import org.bson.BsonValue
import org.bson.types.ObjectId

interface UserRepository {
    suspend fun create(user: User): Boolean
    suspend fun update(id: ObjectId, user: UserRequest): Boolean
    suspend fun delete(id: ObjectId): Boolean
    suspend fun getAll(): List<User>
    suspend fun getById(id: ObjectId): User?
    suspend fun getByEmail(email: String): User?
}