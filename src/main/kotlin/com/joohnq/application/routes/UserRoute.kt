package com.joohnq.application.routes

import com.joohnq.application.request.UserRequest
import com.joohnq.domain.entity.User.Companion.toResponse
import com.joohnq.domain.ports.UserRepository
import com.joohnq.exception.UserResponseError
import com.joohnq.exception.UserResponseSuccess
import com.joohnq.mappers.receiveOrRespondBadRequest
import com.joohnq.mappers.respondText
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.types.ObjectId

fun Route.userRoutes(userRepository: UserRepository) {
    route("") {
        get("/users") {
            val users = userRepository.getAll()
            call.respond(users.map { user -> user.toResponse() })
        }
        get("/user/{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(UserResponseError.MissingId)

            userRepository.getById(ObjectId(id))?.let {
                call.respond(it.toResponse())
            } ?: call.respondText("No records found for id $id")
        }
//        post("/user") {
//            val userReq = call.receiveOrRespondBadRequest<UserRequest>() ?: return@post
//            val user = userReq.toDomain()
//            val res = userRepository.create(user)
//            call.respond(
//                if (res) user.toResponse() else UserResponseError.ErrorOnCreate
//            )
//        }
        patch("/user/{id}") {
            val id = call.parameters["id"] ?: return@patch call.respondText(UserResponseError.MissingId)
            val userReq = call.receiveOrRespondBadRequest<UserRequest>() ?: return@patch
            val res = userRepository.update(ObjectId(id), userReq)
            call.respondText(
                if (res) UserResponseSuccess.UpdatedSuccess else UserResponseError.ErrorOnUpdate
            )
        }
        delete("/user/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(UserResponseError.MissingId)

            val res = userRepository.delete(ObjectId(id))
            call.respondText(
                if (res) UserResponseSuccess.DeleteSuccess else UserResponseError.ErrorOnUpdate
            )
        }
    }
}