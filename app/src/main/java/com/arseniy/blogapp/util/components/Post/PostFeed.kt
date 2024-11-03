package com.arseniy.blogapp.util.components.Post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.arseniy.blogapp.data.local.post.PostEntity
import com.arseniy.blogapp.ui.theme.Grey222
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostFeed(posts : LazyPagingItems<PostEntity>, onUsernameClick :(String) -> Unit, modifier: Modifier = Modifier){

    Box(
        modifier = modifier
    ) {
        if (posts.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black
            )
        } else if (posts.itemCount == 0) {
            Text(
                text = "No posts yet!",
                color = Grey222,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(22.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(posts, key ={
                    it.id
                }) { post ->

                    if (post != null) {

                        Post(
                            onUsernameClick = { onUsernameClick(post.username) },
                            name = post.name,
                            body = post.body,
                            likes = post.likes,
                            liked = false,
                            date = LocalDateTime.parse(post.timePosted).format(
                                DateTimeFormatter.ofPattern("dd-MM-yyyy")
                            ),
                            comments = 0,
                            onCommentsClick = {},
                            onLikeClick = {},
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }

                }


                item {

                    if (posts.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(
                                Alignment.Center
                            ), color = Color.Black
                        )
                    }


                }

            }


        }
    }

}