package com.arseniy.blogapp.data.remote.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.arseniy.blogapp.data.local.db.Db
import com.arseniy.blogapp.data.local.enteties.PostEntity
import com.arseniy.blogapp.data.repository.Repository


@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator(private val repository: Repository, private val db :Db) : RemoteMediator<Int, PostEntity>(){

    var pageNumber = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostEntity>
    ): MediatorResult {


            var page = when (loadType) {
                LoadType.REFRESH -> {
                    pageNumber = 0
                    pageNumber
                }
                LoadType.PREPEND -> {0}
                LoadType.APPEND -> pageNumber
            }

            if (loadType == LoadType.PREPEND) {
               return  MediatorResult.Success(endOfPaginationReached = true)
            }


            val limit = state.config.pageSize
            val offset = page * limit

            val result  = repository.getFeed(limit = limit.toLong(), offset = offset.toLong());



            return result.fold( {
                MediatorResult.Error(it)


            },{ posts->

                db.withTransaction {

                    if (loadType == LoadType.REFRESH) {
                        repository.clearAllPosts("feed")
                    }

                    repository.insertPosts(posts, source = "feed")

                    pageNumber += 1

                    MediatorResult.Success(endOfPaginationReached = posts.isEmpty())

                }
            })


    }


}