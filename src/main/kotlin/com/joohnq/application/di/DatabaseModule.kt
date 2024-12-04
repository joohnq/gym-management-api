package com.joohnq.application.di

import com.joohnq.connectToMongoDB
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun initDatabaseModule(
    application: Application
): Module = module {
    singleOf<MongoDatabase> (application::connectToMongoDB)
}