package com.arseniy.blogapp.data.remote.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.arseniy.blogapp.data.local.db.Db
import com.arseniy.blogapp.data.local.enteties.UserEntity
import com.arseniy.blogapp.data.repository.Repository
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator @Inject constructor(private val repository: Repository, private val db: Db, private val searchString : String) : RemoteMediator<Int, UserEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {


        val pageNumber = when(loadType){
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> 0
            LoadType.APPEND -> state.pages.lastIndex + 1
        }

        val result = repository.searchUsers(searchString = searchString, pageNumber,state.config.pageSize)

       return result.fold({
            MediatorResult.Error(it)
        },{ usersPage ->
            db.withTransaction {

                repository.insertUsers(usersPage.content, "search")

                pageNumber + 1

                MediatorResult.Success(endOfPaginationReached = usersPage.empty)

            }
        })


    }


}