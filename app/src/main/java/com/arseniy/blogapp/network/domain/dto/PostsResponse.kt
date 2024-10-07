package com.arseniy.blogapp.network.domain.dto

import com.arseniy.blogapp.feed.domain.model.Post
import kotlinx.serialization.Serializable

@Serializable
data class PostsResponse(
   val posts : List<Post>
)