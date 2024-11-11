package com.arseniy.blogapp.network.domain.dto

import kotlinx.datetime.LocalDateTime

data class UpdateDto (

    val id : Long,

    val type : Int,

    val name: String,
    val username : String,
    val profilePicture  : String,

    val date  : LocalDateTime
)