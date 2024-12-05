package com.joohnq.application

data class AppConfig(
    val ktor: KtorConfig,
    val jwt: JwtConfig
)

data class KtorConfig(
    val deployment: DeploymentConfig,
    val application: ApplicationConfig
)

data class DeploymentConfig(
    val port: Int? = null
)

data class ApplicationConfig(
    val modules: List<String>
)

data class JwtConfig(
    val issuer: String,
    val domain: String,
    val audience: String,
    val realm: String
)