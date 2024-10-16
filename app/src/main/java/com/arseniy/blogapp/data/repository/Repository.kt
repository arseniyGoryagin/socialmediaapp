package com.arseniy.blogapp.data.repository

import androidx.paging.PagingSource
import arrow.core.Either
import com.arseniy.blogapp.auth.domain.dto.TokenResponse
import com.arseniy.blogapp.data.local.enteties.PostEntity
import com.arseniy.blogapp.data.local.enteties.UserEntity
import com.arseniy.blogapp.domain.model.Post
import com.arseniy.blogapp.network.domain.dto.PageResponse
import com.arseniy.blogapp.user.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Repository {

    val shouldLogOut : StateFlow<Boolean>

    // Auth
    suspend fun login(username : String, password : String) : Either<Exception, TokenResponse>

    suspend fun register(email : String, username : String, password : String) : Either<Exception, TokenResponse>


    // Posts
    suspend fun addPost(postBody : String ) : Either<Exception, Post>

    suspend fun getFeed(offset : Long, limit : Long) : Either<Exception, List<Post>>

    suspend fun getUserPosts (username : String , offset : Long, limit : Long) : Either<Exception, List<Post>>


    // User

    suspend fun getUser(username : String ) : Either<Exception, User>

    suspend fun getMe() : Either<Exception, User>

    // Search users

    suspend fun searchUsers(searchString : String, page : Int, size  : Int) :Either<Exception, PageResponse<User>>


    // Db operations

    // Posts
    suspend fun clearAllPosts(source : String)

    suspend fun insertPosts(posts : List<Post>, source : String)

    fun getPostPagingSource(source : String) : PagingSource<Int, PostEntity>


    // Users
    suspend fun insertUsers(users : List<User>, source : String)

    fun getUsersSource(source : String) : PagingSource<Int, UserEntity>



    // Token

    fun getTokenFlow() : Flow<String>

    suspend fun logOut()
    suspend fun shouldLogOut()

}