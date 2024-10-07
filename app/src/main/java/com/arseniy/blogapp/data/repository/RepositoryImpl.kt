package com.arseniy.blogapp.data.repository

import androidx.paging.PagingSource
import arrow.core.Either
import com.arseniy.blogapp.auth.domain.dto.LoginRequest
import com.arseniy.blogapp.auth.domain.dto.RegisterRequest
import com.arseniy.blogapp.auth.domain.dto.TokenResponse
import com.arseniy.blogapp.data.local.TokenManager
import com.arseniy.blogapp.data.local.db.Db
import com.arseniy.blogapp.data.local.enteties.PostEntity
import com.arseniy.blogapp.feed.domain.model.Post
import com.arseniy.blogapp.network.domain.ApiService
import com.arseniy.blogapp.network.domain.dto.ErrorResponse
import com.arseniy.blogapp.network.domain.dto.PostRequest
import com.arseniy.blogapp.network.domain.dto.PostsResponse
import com.arseniy.blogapp.user.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService,
                                         private val tokenManager : TokenManager,
                                         private val db: Db) : Repository
{


    private val postDao = db.postDao



    override suspend fun login(
        username: String,
        password: String
    ): Either<Exception, TokenResponse> {

        return Either.catch {
            val loginRequest : LoginRequest = LoginRequest(username, password);
            val resp = apiService.login(loginRequest)
            tokenManager.saveToken(resp.token)
            resp
        }.mapLeft {
            it as Exception
        }
    }

    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): Either<Exception, TokenResponse> {
        return Either.catch {
            val registerRequest : RegisterRequest = RegisterRequest(username, password, email);
            val resp = apiService.register(registerRequest)


            tokenManager.saveToken(resp.token)
            resp
        }.mapLeft {
            it as Exception
        }
    }

    override suspend fun addPost(postBody: String): Either<Exception, Post> {
        return Either.catch {
            val postRequest = PostRequest(postBody)
            val value = "Bearer " + tokenManager.getToken().first()
            apiService.addPost(postRequest, value)
        }.mapLeft { it as Exception }
    }

    override suspend fun getFeed(offset: Long, limit: Long): Either<Exception, List<Post>> {
        return Either.catch {
            val value = "Bearer " + tokenManager.getToken().first()
            apiService.getFeed(token = value, offset = offset, limit = limit)
        }.mapLeft { it as Exception }
    }

    override suspend fun getUserPosts(
        username: String,
        offset: Long,
        limit: Long
    ): Either<Exception, List<Post>> {
       TODO()
    }

    override suspend fun getUser(username: String): Either<Exception, User> {
        TODO("Not yet implemented")
    }

    override suspend fun clearAllPosts() {
       postDao.deleteAll()
    }

    override suspend fun insertPosts(posts : List<Post>) {
        postDao.insertPosts(posts.map { PostEntity.toPostEntity(it) })
    }

    override fun getPostPagingSource(): PagingSource<Int, PostEntity> {
       return  postDao.getPosts()
    }

    override fun getTokenFlow(): Flow<String> {
        return tokenManager.getToken()
    }


}