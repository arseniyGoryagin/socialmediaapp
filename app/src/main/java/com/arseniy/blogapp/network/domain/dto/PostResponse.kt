package com.arseniy.blogapp.network.domain.dto

import com.arseniy.blogapp.domain.model.Post
import kotlinx.serialization.Serializable


@Serializable
data  class PostResponse (

    val post : Post,

    )