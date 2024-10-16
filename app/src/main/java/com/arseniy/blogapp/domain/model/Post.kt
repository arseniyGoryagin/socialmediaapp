package com.arseniy.blogapp.domain.model

import com.arseniy.blogapp.user.domain.model.User
import kotlinx.serialization.Serializable


@Serializable
data class Post(

    val id : Long,

    val body : String,

    val likes : Long,

    val user : User,
    val edited : Boolean,

)