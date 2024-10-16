package com.arseniy.blogapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arseniy.blogapp.data.local.dao.PostDao
import com.arseniy.blogapp.data.local.dao.UserDao
import com.arseniy.blogapp.data.local.enteties.PostEntity
import com.arseniy.blogapp.data.local.enteties.UserEntity


@Database(
    entities = [PostEntity::class, UserEntity::class],
    version = 1
)
abstract class Db : RoomDatabase() {




    abstract val postDao  :PostDao
    abstract val usersDao : UserDao



    companion object{

            var DB : Db? = null

            fun getDb(context : Context) : Db{

                if(DB != null){
                    return DB!!
                }


                synchronized(this){

                    DB = Room.databaseBuilder(

                        context,
                        Db::class.java,
                        "app.db"
                    ).build()


                    return DB!!
                }

            }


    }


}