package com.joohnq.application.secutiry.hashing

data class SaltedHash(
    val hash: String,
    val salt: String
)
