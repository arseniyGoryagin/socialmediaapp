package com.arseniy.blogapp.util.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Post(username: String, body : String, likes : Long, liked : Boolean ,onUsernameClick : (String) -> Unit, modifier: Modifier =Modifier, profileImgUrl : String = "",){

    Column(

        modifier = modifier

    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            SubComposeProfilePic(profileImgUrl = profileImgUrl)
            Text(text = username , fontWeight = FontWeight.Bold, modifier =  modifier.clickable { onUsernameClick(username) })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = body)
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier =Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            if(liked) {
                Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.Red)
            }
            else{
                Icon(Icons.Default.FavoriteBorder, contentDescription = null)
            }

            Icon(Icons.Default.MailOutline, contentDescription = null)
        }
    }





}

@Preview
@Composable
fun perr(){
   // Post(name = "Jason", body = "Hello guys this is my first post", likes = 100, liked = false)
}