package com.arseniy.blogapp.network.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id : Long,
    val username : String,
    val profilePicture : String,
    val description : String
)