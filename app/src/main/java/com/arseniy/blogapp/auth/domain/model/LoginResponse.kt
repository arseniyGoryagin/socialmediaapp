package com.arseniy.blogapp.auth.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse(
    val token : String
)