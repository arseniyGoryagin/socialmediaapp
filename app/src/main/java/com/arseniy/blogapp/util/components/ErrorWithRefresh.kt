package com.arseniy.blogapp.util.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign


@Composable
fun ErrorWithRefresh(onRefreshClick  : () -> Unit, errorMessage : String ){


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Text(errorMessage, color = Color.LightGray, textAlign = TextAlign.Center)
        IconButton(onClick = onRefreshClick){
            Icon(Icons.Filled.Refresh, contentDescription = "Retyr loading")
        }
    }


}