package com.joohnq.di

import com.joohnq.application.AppConfig
import com.sksamuel.hoplite.ConfigLoader
import org.koin.dsl.module

val appModule = module {
    single<AppConfig> { ConfigLoader().loadConfigOrThrow<AppConfig>("/application.conf") }
}