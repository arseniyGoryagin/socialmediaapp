package com.arseniy.blogapp.network.domain.dto

import kotlinx.serialization.Serializable


@Serializable
data class PostRequest (
    val body : String
)