package com.arseniy.blogapp.util.components.Profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arseniy.blogapp.util.components.SubComposeProfilePic


sealed interface ActionType{

    object Follow : ActionType
    object UnFollow : ActionType
    object EditProfile : ActionType

}


@Composable
fun ProfileHeader(
    username : String,
    name : String,
    description : String,
    profilePicture :String?,
    followers : Long,
    following : Long,
    action : ActionType,
    onFollowingClick : () -> Unit,
    onFollowersClick : () -> Unit,
    modifier: Modifier = Modifier,
    onActionClick : () -> Unit) {

    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            SubComposeProfilePic(
            modifier = Modifier.width(40.dp),
            profileImgUrl = profilePicture,
            )


            when(action){
                ActionType.EditProfile -> EditProfileButton(onClick = onActionClick)
                ActionType.Follow -> FollowButton(onClick = onActionClick)
                ActionType.UnFollow -> UnFollowButton(onClick = onActionClick)
            }

        }

        Spacer(modifier = modifier.height(10.dp))

        Text(text = name, fontSize = 22.sp)

        Spacer(modifier = modifier.height(8.dp))

        Text(text = "@$username", fontSize = 16.sp, color = Color.Gray)

        Spacer(modifier = modifier.height(15.dp))

        Text(text = description, fontSize = 16.sp)

        Spacer(modifier = modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = modifier.fillMaxWidth()
        ){

            Text(text = buildAnnotatedString {

                withStyle(style = SpanStyle(color = Color.Black)){
                    append("$followers ")

                }

                withStyle(style = SpanStyle(color = Color.Gray)){
                    append("Following")

                }


            }, fontSize = 14.sp, modifier = Modifier.clickable { onFollowingClick() })

            Spacer(modifier = modifier.width(10.dp))

            Text(text = buildAnnotatedString {

                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("$following ")

                }

                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("Followers")

                }


            }, fontSize = 14.sp, modifier = Modifier.clickable { onFollowersClick() })

        }




    }


}


@Preview
@Composable
fun preg(){

    ProfileHeader(name = "Arseniy", username = "bigboy",description = "Hello guys", followers = 100, following = 100, onFollowingClick = {}, onFollowersClick ={}, profilePicture = "", action = ActionType.EditProfile, onActionClick = {})

}


