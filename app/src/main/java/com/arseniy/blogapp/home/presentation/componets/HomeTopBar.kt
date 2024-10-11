package com.arseniy.blogapp.home.presentation.componets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arseniy.blogapp.util.components.SearchBar


@Composable
fun HomeTopBar(onProfileClick : () -> Unit, onSearch : (String) -> Unit ,searchValue : String){


    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                Icons.Filled.AccountCircle,
                contentDescription = "Profile",
                modifier = Modifier.clickable { onProfileClick() })

            SearchBar(onChange = onSearch, value = searchValue)

        }

    }


}