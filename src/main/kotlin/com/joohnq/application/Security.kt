package com.joohnq.application

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.joohnq.application.secutiry.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.koin.ktor.ext.inject

fun Application.configureSecurity(
    appConfig: AppConfig,
    tokenConfig: TokenConfig
) {
    authentication {
        jwt {
            realm = appConfig.jwt.realm
            verifier(
                JWT.require(Algorithm.HMAC256(tokenConfig.secret))
                    .withAudience(tokenConfig.audience)
                    .withIssuer(tokenConfig.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(tokenConfig.audience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
}