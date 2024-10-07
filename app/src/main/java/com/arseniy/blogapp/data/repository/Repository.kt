package com.arseniy.blogapp.data.repository

import androidx.paging.PagingSource
import arrow.core.Either
import com.arseniy.blogapp.auth.domain.dto.TokenResponse
import com.arseniy.blogapp.data.local.enteties.PostEntity
import com.arseniy.blogapp.feed.domain.model.Post
import com.arseniy.blogapp.network.domain.dto.ErrorResponse
import com.arseniy.blogapp.network.domain.dto.PostResponse
import com.arseniy.blogapp.network.domain.dto.PostsResponse
import com.arseniy.blogapp.network.domain.dto.UserResponse
import com.arseniy.blogapp.user.domain.model.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {




    // Auth
    suspend fun login(username : String, password : String) : Either<Exception, TokenResponse>

    suspend fun register(email : String, username : String, password : String) : Either<Exception, TokenResponse>


    // Posts
    suspend fun addPost(postBody : String ) : Either<Exception, Post>

    suspend fun getFeed(offset : Long, limit : Long) : Either<Exception, List<Post>>

    suspend fun getUserPosts (username : String , offset : Long, limit : Long) : Either<Exception, List<Post>>



    // User

    suspend fun getUser(username : String ) : Either<Exception, User>




    // Db operations


    suspend fun clearAllPosts()

    suspend fun insertPosts(posts : List<Post>)

    fun getPostPagingSource() : PagingSource<Int, PostEntity>



    // Token

    fun getTokenFlow() : Flow<String>

}