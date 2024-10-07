package com.arseniy.blogapp.util.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BottomErrorMessage(message : String, modifier: Modifier = Modifier){


    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        Text(text = message, color = Color.Red)
    }

}