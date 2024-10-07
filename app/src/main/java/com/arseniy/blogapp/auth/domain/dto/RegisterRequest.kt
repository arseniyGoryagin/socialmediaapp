package com.arseniy.blogapp.auth.domain.dto

import kotlinx.serialization.Serializable


@Serializable
data class RegisterRequest (

    val username : String,
    val password : String,
    val email : String




)