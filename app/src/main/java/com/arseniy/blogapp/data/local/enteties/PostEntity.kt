package com.arseniy.blogapp.data.local.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arseniy.blogapp.feed.domain.model.Post
import org.intellij.lang.annotations.Identifier


@Entity(tableName = "posts_table")
data class  PostEntity (

    @PrimaryKey
    val id :Long,

    val body : String,

    val likes : Long,

    val username : String,
    val edited : Boolean




){

    companion object{


        fun toPostEntity(post : Post  ) : PostEntity{

            return PostEntity(
                id = post.id,
                likes = post.likes,
                body = post.body,
                username = post.username,
                edited = post.edited
            )

        }


    }


}