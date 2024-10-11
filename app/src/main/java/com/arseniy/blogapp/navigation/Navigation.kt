package com.arseniy.blogapp.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    object Main : Routes

    @Serializable
    object Home : Routes

    @Serializable
    object AddPost : Routes

    @Serializable
    object Notifications : Routes

    @Serializable
    data class Profile(val username : String) :Routes

    @Serializable
    object MyProfile :Routes



    @Serializable
    object Auth : Routes

    @Serializable
    object Welcome : Routes

    @Serializable
    object Register :Routes

    @Serializable
    object Login : Routes


}

