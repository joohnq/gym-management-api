package com.joohnq.di

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(
            appModule,
            initDatabaseModule(this@configureKoin),
            repositoryModule,
            securityModule
        )
    }
}

