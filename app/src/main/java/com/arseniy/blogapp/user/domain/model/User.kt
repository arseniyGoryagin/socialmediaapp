package com.arseniy.blogapp.user.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class User (

    val id : Long,

    val username : String,

    val profilePicture : String,

    val description : String
)