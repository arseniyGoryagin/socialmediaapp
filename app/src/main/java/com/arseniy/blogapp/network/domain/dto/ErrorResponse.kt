package com.arseniy.blogapp.network.domain.dto

import kotlinx.serialization.Serializable


@Serializable
data class ErrorResponse (
    val message : String
)