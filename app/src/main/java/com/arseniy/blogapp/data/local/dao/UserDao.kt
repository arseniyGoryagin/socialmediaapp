package com.arseniy.blogapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.arseniy.blogapp.data.local.enteties.UserEntity


@Dao
interface UserDao {


    @Query("Select * from users_table where source = :source")
    fun getUserPagingSource(source : String) : PagingSource<Int, UserEntity>

    @Query("delete from users_table where source = :source")
    fun deleteBySource(source : String)

    @Upsert
    fun insertUsers(users : List<UserEntity>)


}