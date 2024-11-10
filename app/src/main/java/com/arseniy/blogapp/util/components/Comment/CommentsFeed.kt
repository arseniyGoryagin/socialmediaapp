package com.arseniy.blogapp.util.components.Comment

import android.annotation.SuppressLint
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
import com.arseniy.blogapp.data.local.comment.CommentEntity
import com.arseniy.blogapp.domain.model.Comment
import com.arseniy.blogapp.domain.model.Post
import com.arseniy.blogapp.navigation.Routes
import com.arseniy.blogapp.ui.theme.Grey222
import com.arseniy.blogapp.util.components.ErrorWithRefresh
import com.arseniy.blogapp.util.components.Feed
import com.arseniy.blogapp.util.components.Post.Post
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
@SuppressLint("NewApi")
fun CommentsFeed(comments : LazyPagingItems<CommentEntity>, onUsernameClick :(String) -> Unit, modifier: Modifier = Modifier){

    Feed(
        modifier = modifier,
        items = comments,
        Item = { comment ->

        Comment(
            name =  comment.name ,
            body = comment.body,
            profilePictureUrl = comment.profilePicture,
            date = LocalDateTime.parse(comment.timePosted).format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy")
            ),
            likes = comment.likes,
            onProfileClick = {onUsernameClick(comment.username)})

    },
        emptyMessage = "No comments to show",
        onRefreshClick = { /*TODO*/ })
}