package com.arseniy.blogapp.feed.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Post(

    val id : Long,

    val body : String,

    val likes : Long,

    val username : String,
    val edited : Boolean


)