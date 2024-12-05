package com.joohnq.di

import com.joohnq.MongoClientBuilder
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun initDatabaseModule(
    application: Application
): Module = module {
    singleOf<MongoClient>(MongoClientBuilder()::build)
    single<MongoDatabase> {
        val client = get<MongoClient>()
        val database = client.getDatabase("common")
        application.monitor.subscribe(ApplicationStopped) {
            client.close()
        }
        database
    }
}