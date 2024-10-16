package com.arseniy.blogapp.network.domain

import com.arseniy.blogapp.auth.domain.dto.LoginRequest
import com.arseniy.blogapp.auth.domain.dto.RegisterRequest
import com.arseniy.blogapp.auth.domain.dto.TokenResponse
import com.arseniy.blogapp.domain.model.Post
import com.arseniy.blogapp.network.domain.dto.PageResponse
import com.arseniy.blogapp.network.domain.dto.PostRequest
import com.arseniy.blogapp.user.domain.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
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



    @GET("posts/user/{username}")
    suspend fun getUserPost(@Path("username") username : String, @Query("limit") limit : Long, @Query("offset") offset : Long, @Header("Authorization") token : String): List<Post>



    @POST("posts/{id}")
    suspend fun editPosts(@Body loginRequest: PostRequest,@Path("id") postId : Long, @Header("Authorization") token : String): String;

    @POST("posts/{id}")
    suspend fun deletePosts(@Body loginRequest: PostRequest, @Path("id") postId : Long, @Header("Authorization") token : String): String;


    // User
    @GET("users/{username}")
    suspend fun getUser( @Path("username") username : String, @Header("Authorization") token : String) : User;

    @GET("users/me")
    suspend fun getMe( @Header("Authorization") token : String) : User;

    // Search
    @GET("users/search")
    suspend fun searchUser(  @Query("username") searchString : String, @Query("page") page : Int,  @Query("size") size : Int, @Header("Authorization") token : String) : PageResponse<User>;


}