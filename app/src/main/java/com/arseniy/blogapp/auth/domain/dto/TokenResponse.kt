package com.arseniy.blogapp.auth.domain.dto

import kotlinx.serialization.Serializable


@Serializable
data class TokenResponse (
    val token : String,
    val refreshToken : String,
)