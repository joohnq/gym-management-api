package com.joohnq.application.secutiry.token

interface TokenService {
    fun generate(config: TokenConfig, vararg claims: TokenClaim): String
}