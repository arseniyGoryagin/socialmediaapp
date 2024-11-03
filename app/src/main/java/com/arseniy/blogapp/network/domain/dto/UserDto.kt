package com.arseniy.blogapp.network.domain.dto

import kotlinx.serialization.Serializable


@Serializable
data class User (

    val id : Long,

    val username : String,

    val name : String,

    val profilePicture : String,

    val description : String,

    val followerCount : Long,
    val followsCount : Long,

    val isOwn : Boolean,
    val isFollowing : Boolean
)