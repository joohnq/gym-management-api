package com.joohnq.infrastructure.repository

import com.joohnq.application.request.UserRequest
import com.joohnq.domain.entity.User
import com.joohnq.domain.ports.UserRepository
import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId

class UserRepositoryImpl(private val database: MongoDatabase) : UserRepository {
    private val collection = database.getCollection<User>(USER_COLLECTION)

    override suspend fun create(user: User): Boolean =
        try {
            collection.insertOne(user)
            true
        } catch (e: MongoException) {
            System.err.println("Unable to insert due to an error: $e")
            false
        }

    override suspend fun update(id: ObjectId, user: UserRequest): Boolean {
        try {
            val query = Filters.eq("_id", id)
            val updates = Updates.combine(
                Updates.set(User::firstname.name, user.firstname),
                Updates.set(User::lastname.name, user.lastname),
            )
            val options = UpdateOptions().upsert(true)
            collection.updateOne(query, updates, options)
            return true
        } catch (e: MongoException) {
            System.err.println("Unable to update due to an error: $e")
        }
        return false
    }

    override suspend fun delete(id: ObjectId): Boolean {
        try {
            collection.deleteOne(Filters.eq("_id", id))
            return true
        } catch (e: MongoException) {
            System.err.println("Unable to delete due to an error: $e")
        }
        return false
    }

    override suspend fun getAll(): List<User> =
        try {
            collection.withDocumentClass<User>()
                .find()
                .sort(Sorts.ascending(User::id.name))
                .toList()
        } catch (e: Exception) {
            System.err.println("Unable to get users due to an error: $e")
            emptyList()
        }

    override suspend fun getById(id: ObjectId): User? =
        try {
            collection
                .find(Filters.eq("_id", id))
                .firstOrNull()
        } catch (e: Exception) {
            System.err.println("Unable to get user by id due to an error: $e")
            null
        }

    companion object {
        const val USER_COLLECTION = "users"
    }
}