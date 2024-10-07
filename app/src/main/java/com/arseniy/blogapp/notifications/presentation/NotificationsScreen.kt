package com.arseniy.blogapp.notifications.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arseniy.blogapp.Routes
import com.arseniy.blogapp.util.components.MainBottomBar


@Composable
fun NotificationsScreen(onHomeClick : ()-> Unit, onAddPostClick : ()-> Unit) {
   Box(
       modifier = Modifier.fillMaxSize()
   ) {
       
       Text(text = "Notifications")
       
       MainBottomBar(
           modifier = Modifier
               .padding(horizontal = 32.dp)
               .padding(bottom = 32.dp)
               .align(Alignment.BottomCenter),
           onHomeClick = onHomeClick,
           onNotificationsClick = {},
           onNewPostClick = onAddPostClick,
           currentRoute = Routes.Notifications
       )
   }
}