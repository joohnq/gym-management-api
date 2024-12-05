package com.joohnq.di

import com.joohnq.application.AppConfig
import com.joohnq.application.secutiry.hashing.HashingService
import com.joohnq.application.secutiry.hashing.SHA256HashingService
import com.joohnq.application.secutiry.token.JwtTokenService
import com.joohnq.application.secutiry.token.TokenConfig
import com.joohnq.application.secutiry.token.TokenService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val securityModule = module {
    singleOf(::SHA256HashingService) bind HashingService::class
    singleOf(::JwtTokenService) bind TokenService::class
    single<TokenConfig> {
        val config = get<AppConfig>()
        TokenConfig(
            issuer = config.jwt.issuer,
            audience = config.jwt.audience,
            expiresIn = 365L * 1000L * 60L * 60L * 24L,
            secret = System.getenv("JWT_SECRET")
        )
    }
}
