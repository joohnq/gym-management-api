
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.joohnq"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation("io.ktor:ktor-server-swagger-jvm")
    implementation("io.ktor:ktor-server-resources:3.0.1")
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.2.1")
    implementation("org.mongodb:bson-kotlinx:5.2.1")
    implementation("io.ktor:ktor-client-cio-jvm:3.0.1")
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    implementation("io.ktor:ktor-server-call-logging-jvm:3.0.1")
    implementation("io.ktor:ktor-server-auth-jvm:3.0.1")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:3.0.1")
    implementation("ch.qos.logback:logback-classic:1.5.12")

    implementation("commons-codec:commons-codec:1.17.1")

    implementation("com.sksamuel.hoplite:hoplite-core:2.9.0")
    implementation("com.sksamuel.hoplite:hoplite-hocon:2.9.0")
}
