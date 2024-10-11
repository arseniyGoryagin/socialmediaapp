package com.arseniy.blogapp.util.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.SubcomposeAsyncImage


@Composable
fun ProfileHeader(username : String?, description : String, profilePicture :String?, modifier: Modifier = Modifier) {


    Box(
        modifier = modifier,
    ) {

        SubComposeProfilePic(profileImgUrl = profilePicture,  modifier = Modifier.align(Alignment.TopStart))
        Text(text = username ?: "", modifier = Modifier.align(Alignment.Center))

    }


}


