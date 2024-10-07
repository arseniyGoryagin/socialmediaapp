package com.arseniy.blogapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import com.arseniy.blogapp.data.local.enteties.PostEntity
import androidx.room.Query
import androidx.room.Upsert
import com.arseniy.blogapp.feed.domain.model.Post


@Dao
interface PostDao {

    @Query("Select * from posts_table")
    fun getPosts() : PagingSource<Int, PostEntity>

    @Upsert
    suspend fun insertPosts(posts : List<PostEntity>)

    @Query("delete from posts_table")
    suspend fun deleteAll()


}