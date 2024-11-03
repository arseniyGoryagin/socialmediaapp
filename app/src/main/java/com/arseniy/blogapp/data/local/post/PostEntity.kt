package com.arseniy.blogapp.data.local.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arseniy.blogapp.network.domain.dto.Post


@Entity(tableName = "posts_table")
data class  PostEntity (

    @PrimaryKey
    val id :Long,
    val body : String,
    val likes : Long,
    val edited : Boolean,



    val username : String,
    val description : String,
    val profilePicture : String,
    val userId : Long,

    val source : String,


){

    companion object{


        fun toPostEntity(post : Post, source  : String) : PostEntity{

            return PostEntity(
                id = post.id,
                likes = post.likes,
                body = post.body,
                edited = post.edited,


                username = post.user.username,
                description = post.user.description,
                profilePicture = post.user.profilePicture,
                userId = post.user.id,
                source = source,
            )

        }


    }


}