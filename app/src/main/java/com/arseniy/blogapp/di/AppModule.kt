package com.arseniy.blogapp.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.arseniy.blogapp.data.local.TokenManager
import com.arseniy.blogapp.data.local.db.Db
import com.arseniy.blogapp.data.local.enteties.PostEntity
import com.arseniy.blogapp.data.remote.mediators.PostRemoteMediator
import com.arseniy.blogapp.data.repository.Repository
import com.arseniy.blogapp.data.repository.RepositoryImpl
import com.arseniy.blogapp.network.domain.ApiService
import com.arseniy.blogapp.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesTokenManager(@ApplicationContext  context: Context) : TokenManager{
        return TokenManager(context)
    }

    @Provides
    @Singleton
    fun providesAuthRepository(apiService : ApiService, tokenManager: TokenManager, db: Db) : Repository {
        return RepositoryImpl(apiService, tokenManager, db)
    }


    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit{
        return RetrofitClient.getRetrofit()
    }


    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) : Db{
        return Db.getDb(context)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providesPostsPager(repository: Repository, db : Db) : Pager<Int, PostEntity>{
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 0),
            remoteMediator = PostRemoteMediator(
                repository,
                db
            ),
            pagingSourceFactory = {
                repository.getPostPagingSource()
            }
        )
    }



    @Provides
    @Singleton
    fun provideApiService(retrofit : Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }


}