package com.joohnq.application.di

import com.joohnq.domain.ports.UserRepository
import com.joohnq.infrastructure.repository.UserRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::UserRepositoryImpl) bind UserRepository::class
}