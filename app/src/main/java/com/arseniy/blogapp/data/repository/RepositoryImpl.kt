package com.arseniy.blogapp.data.repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingSource
import arrow.core.Either
import coil.network.HttpException
import com.arseniy.blogapp.auth.domain.dto.LoginRequest
import com.arseniy.blogapp.auth.domain.dto.RegisterRequest
import com.arseniy.blogapp.auth.domain.dto.TokenResponse
import com.arseniy.blogapp.data.local.TokenManager
import com.arseniy.blogapp.data.local.db.Db
import com.arseniy.blogapp.data.local.enteties.PostEntity
import com.arseniy.blogapp.data.local.enteties.UserEntity
import com.arseniy.blogapp.domain.model.Post
import com.arseniy.blogapp.network.domain.ApiService
import com.arseniy.blogapp.network.domain.dto.PageResponse
import com.arseniy.blogapp.network.domain.dto.PostRequest
import com.arseniy.blogapp.user.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService,
                                         private val tokenManager : TokenManager,
                                         private val db: Db) : Repository
{


    private val postDao = db.postDao
    private val usersDao = db.usersDao

    private var _shouldLogOut = MutableStateFlow(false)
    override val shouldLogOut : StateFlow<Boolean> = _shouldLogOut


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
            handleException(it as Exception)
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
            handleException(it as Exception)
        }
    }

    override suspend fun addPost(postBody: String): Either<Exception, Post> {
        return Either.catch {
            val postRequest = PostRequest(postBody)
            val value = "Bearer " + tokenManager.getToken().first()
            apiService.addPost(postRequest, value)
        }.mapLeft {handleException(it as Exception) }
    }

    override suspend fun getFeed(offset: Long, limit: Long): Either<Exception, List<Post>> {
        return Either.catch {
            val value = "Bearer " + tokenManager.getToken().first()
            apiService.getFeed(token = value, offset = offset, limit = limit)
        }.mapLeft { handleException(it as Exception) }
    }

    override suspend fun getUserPosts(
        username: String,
        offset: Long,
        limit: Long
    ): Either<Exception, List<Post>> {
        return Either.catch {
            val value = "Bearer " + tokenManager.getToken().first()
            apiService.getUserPost(username = username, token = value, offset = offset, limit = limit)
        }.mapLeft { handleException(it as Exception) }
    }

    override suspend fun getUser(username: String): Either<Exception, User> {

        println("Username ==== " + username)

        return Either.catch {
            val value = "Bearer " + tokenManager.getToken().first()
            apiService.getUser(username = username, token = value)
        }.mapLeft {
            handleException(it as Exception)
        }
    }



    override suspend fun getMe(): Either<Exception, User> {
        return Either.catch {
            val value = "Bearer " + tokenManager.getToken().first()
            apiService.getMe(token = value)
        }.mapLeft { handleException(it as Exception) }
    }

    override suspend fun searchUsers(
        username: String,
        page: Int,
        size: Int
    ): Either<Exception, PageResponse<User>> {
        return Either.catch {
            val value = "Bearer " + tokenManager.getToken().first()
            apiService.searchUser(username, page, size, token = value)
        }.mapLeft { handleException(it as Exception) }
    }

    override suspend fun clearAllPosts(source : String) {
       postDao.deleteBySource(source)
    }

    override suspend fun insertPosts(posts : List<Post>, source : String) {
        postDao.insertPosts(posts.map { PostEntity.toPostEntity(source = source, post = it) })
    }

    override suspend fun insertUsers(users : List<User>, source : String) {
        usersDao.insertUsers(users.map {UserEntity.toUserEntity(it, source)})
    }

    override fun getUsersSource(source: String): PagingSource<Int, UserEntity> {
        return usersDao.getUserPagingSource(source)
    }

    override fun getPostPagingSource(source : String): PagingSource<Int, PostEntity> {
       return  postDao.getPosts(source)
    }

    override fun getTokenFlow(): Flow<String> {
        return tokenManager.getToken()
    }

    override suspend fun logOut() {
        clearAllPosts("user")
        clearAllPosts("feed")
        tokenManager.deleteToken()
        _shouldLogOut.value = false
    }

    override suspend fun shouldLogOut() {
        _shouldLogOut.value = true
    }

    private fun handleException(e : Exception ) : Exception{



        when(e){
            is retrofit2.HttpException ->{
                if(e.code() == 403){
                    _shouldLogOut.value =true;
                }
            }

        }
        return e
    }


}