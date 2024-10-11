package com.arseniy.blogapp.util.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.SubcomposeAsyncImage

@Composable
fun SubComposeProfilePic(profileImgUrl : String?, modifier: Modifier = Modifier){
    SubcomposeAsyncImage(
        model = profileImgUrl,
        contentDescription = "profile image",
        error = {
            Icon(
                Icons.Filled.AccountCircle,
                contentDescription = null
            )
        },
        loading = {
            Box {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        },
        modifier = modifier
    )
}