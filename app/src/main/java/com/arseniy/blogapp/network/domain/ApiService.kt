package com.arseniy.blogapp.network.domain

import arrow.core.Either
import com.arseniy.blogapp.auth.domain.dto.LoginRequest
import com.arseniy.blogapp.auth.domain.dto.RegisterRequest
import com.arseniy.blogapp.auth.domain.dto.TokenResponse
import com.arseniy.blogapp.feed.domain.model.Post
import com.arseniy.blogapp.network.domain.dto.ErrorResponse
import com.arseniy.blogapp.network.domain.dto.PostRequest
import com.arseniy.blogapp.network.domain.dto.PostResponse
import com.arseniy.blogapp.network.domain.dto.PostsResponse
import com.arseniy.blogapp.network.domain.dto.UserResponse
import com.arseniy.blogapp.user.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    // Auth
    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest) : TokenResponse;

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest):  TokenResponse;



    // Posts

    @POST("posts")
    suspend fun addPost(@Body loginRequest: PostRequest, @Header("Authorization") token : String): Post;

    @GET("posts")
    suspend fun getFeed( @Query("limit") limit : Long, @Query("offset") offset : Long, @Header("Authorization") token : String): List<Post>;
    @GET("posts/{id}")
    suspend fun getPost( @Path("id") postId : Long, @Header("Authorization") token : String): Post

    @GET("posts/{id}")
    suspend fun getUserPost(@Query("limit") limit : Long, @Query("offset") offset : Long,@Query("offset") username : String, @Header("Authorization") token : String): Post



    @POST("posts/{id}")
    suspend fun editPosts(@Body loginRequest: PostRequest,@Path("id") postId : Long, @Header("Authorization") token : String): Either<ErrorResponse, String>;

    @POST("posts/{id}")
    suspend fun deletePosts(@Body loginRequest: PostRequest, @Path("id") postId : Long, @Header("Authorization") token : String): Either<ErrorResponse, String>;


    // User
    @GET("users/{username}")
    suspend fun getUser( @Path("username") username : String, @Header("Authorization") token : String) : Either<ErrorResponse, User>;



}