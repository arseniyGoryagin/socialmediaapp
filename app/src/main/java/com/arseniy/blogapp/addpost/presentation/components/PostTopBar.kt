package com.arseniy.blogapp.addpost.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PostTopBar(onCloseClick : () -> Unit){

    TopAppBar(
        backgroundColor = Color.White,
        elevation = 0.dp

    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Icon(
                Icons.Filled.Clear,
                modifier = Modifier.clickable{

                    onCloseClick()


                },
                contentDescription = null)

        }



    }


}