package com.arseniy.blogapp.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ProfileTopBar(onBackClick :() -> Unit, profileUsername : String ) {


    TopAppBar(
        backgroundColor = Color.White,
        elevation = 0.dp

    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),

        ){

            Icon(
                Icons.Filled.Clear,
                modifier = Modifier.clickable{
                   onBackClick()
                }.align(Alignment.TopStart),
                contentDescription = "Back"

            )

            Text(text = profileUsername, modifier = Modifier.align(Alignment.Center))
        }



    }


}