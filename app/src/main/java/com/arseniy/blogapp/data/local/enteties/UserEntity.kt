package com.arseniy.blogapp.data.local.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arseniy.blogapp.user.domain.model.User

@Entity( tableName = "users_table")
data class UserEntity(

    @PrimaryKey
    val id : Long,

    val username : String,
    val profilePicture: String,
    val description : String,

    val source : String

){

    companion object{



        fun toUserEntity(user:User, source : String) : UserEntity{

            return UserEntity(
                id = user.id,
                username = user.username,
                profilePicture = user.profilePicture,
                description = user.description,
                source = source
            )


        }

    }





}