package com.arseniy.blogapp.util.components.Post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arseniy.blogapp.R
import com.arseniy.blogapp.util.components.SubComposeProfilePic


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Post(
    name: String,
    body : String,
    likes : Long,
    comments : Long,
    date : String,
    liked : Boolean,
    onUsernameClick : () -> Unit,
    onCommentsClick : () -> Unit,
    onLikeClick : () -> Unit,
    modifier: Modifier =Modifier,
    profileImgUrl : String = "",){

    Column(

        modifier = modifier

    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            SubComposeProfilePic(profileImgUrl = profileImgUrl, modifier = Modifier.height(30.dp).width(30.dp))
            Spacer(modifier = modifier.width(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onUsernameClick() }) {
                Text(text = name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(13.dp))
                Text(
                    text = date,
                    color = Color.Gray,
                    fontSize = 12.sp,
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = body, color = Color.Gray)
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                if(liked) {
                    Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.Red)
                }
                else{
                    Icon(Icons.Default.FavoriteBorder, contentDescription = null)
                }

                Spacer(modifier = modifier.width(4.dp))

                Text(text = "$likes")
            }
            Spacer(modifier = modifier.width(22.dp))

            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(painter = painterResource(id = R.drawable.comment_outline), contentDescription = null)

                Spacer(modifier = modifier.width(4.dp))

                Text(text = "$comments")
            }

        }
    }





}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun perr(){
    Surface {
        Post(name = "Jason", body = "Hello guys this is my first post", likes = 100, comments = 100, liked = false,
            onUsernameClick = {}, onCommentsClick = {}, onLikeClick = {}, date = "2020 "
        )
    }

}