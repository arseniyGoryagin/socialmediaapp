package com.arseniy.blogapp.network.domain.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable



@Serializable
data class Post(

    val id : Long,

    val body : String,

    val likes : Long,
    val liked : Boolean,

    val username : String,
    val name : String,
    val profilePicture : String,

    val edited : Boolean,

    val timePosted : LocalDateTime

    )