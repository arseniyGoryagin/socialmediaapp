package com.arseniy.blogapp.util.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arseniy.blogapp.navigation.Routes


@Composable
fun MainBottomBar(onHomeClick : () -> Unit,
                  onNotificationsClick : () -> Unit,
                  onNewPostClick : () -> Unit,
                  currentRoute : Routes,
                  modifier: Modifier = Modifier){





  BottomAppBar(

      modifier= modifier.graphicsLayer {
          shadowElevation = 12F
          clip = true
          shape = (RoundedCornerShape(25.dp))
                                       },
      backgroundColor = Color.White,
      elevation = 12.dp
  ) {
      BottomNavigationItem(selected = currentRoute is Routes.Home, onClick = onHomeClick, icon = {
       Icon(Icons.Filled.Home , contentDescription = null)
      })
      BottomNavigationItem(selected = currentRoute is Routes.Notifications, onClick = onNotificationsClick, icon = {
          Icon(Icons.Filled.Notifications , contentDescription = null)
      })
      BottomNavigationItem(
          selected = currentRoute is  Routes.AddPost,
          onClick = onNewPostClick,
          icon = {
              Box(
                  modifier = Modifier.clip(CircleShape).height(40.dp).width(40.dp).background(Color.Yellow),

              ) {


                  Icon(
                      Icons.Filled.Add,
                      modifier = Modifier.align(Alignment.Center),
                      contentDescription = null,
                      tint = Color.Black
                  )
              }
      })
  }


}


@Preview
@Composable
fun prevvv(){
    MainBottomBar({}, {}, {}, Routes.Home)
}