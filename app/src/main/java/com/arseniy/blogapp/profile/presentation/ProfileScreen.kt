package com.arseniy.blogapp.profile.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.arseniy.blogapp.profile.components.ProfileTopBar
import com.arseniy.blogapp.profile.presentation.viewmodels.ProfileViewModel
import androidx.paging.compose.items
import com.arseniy.blogapp.util.components.Post
import com.arseniy.blogapp.util.components.CenterLoadingCircle
import com.arseniy.blogapp.util.components.ErrorWithRefresh
import com.arseniy.blogapp.util.components.ProfileHeader


@SuppressLint("SuspiciousIndentation")
@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel, onBackClick : () -> Unit, onRetryClick : () -> Unit){


    val uiState by remember{ profileViewModel.profileUiState}
    val uiData = profileViewModel.profileUiData


        Scaffold(


            topBar = {
                ProfileTopBar(onBackClick = onBackClick, profileUsername = uiData.userProfile?.username ?: "")
            },

            content = { innerPadding ->


                if(uiState.isLoadingUser){
                   CenterLoadingCircle()
                }else if(uiState.errorMessage != null){
                   ErrorWithRefresh(onRefreshClick = onRetryClick, errorMessage = uiState.errorMessage!!)
                }
                else{

                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(12.dp)
                        .fillMaxSize()
                ) {

                    ProfileHeader(username = uiData.userProfile?.username,
                        description = "",
                        profilePicture = uiData.userProfile?.profilePicture,
                        modifier = Modifier.fillMaxWidth())

                    Text(text = uiData.userProfile?.description ?: "")

                    val userPosts = profileViewModel.postsFlow?.collectAsLazyPagingItems()

                    if(userPosts?.loadState?.refresh is LoadState.Loading){
                        CenterLoadingCircle()
                    }
                    else if(userPosts?.loadState?.refresh is LoadState.Error){
                        ErrorWithRefresh(onRefreshClick = onRetryClick, errorMessage = "Error loading user posts! ${(userPosts.loadState.refresh as LoadState.Error).error.localizedMessage}")
                    }
                    else {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            items(userPosts!!) { post ->

                                if(post != null) {
                                    Post(
                                        onUsernameClick = {},
                                        username = post.username,
                                        body = post.body,
                                        likes = post.likes,
                                        liked = false,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }

                            }

                            item {

                                if(userPosts.loadState.append is LoadState.Loading){
                                    CenterLoadingCircle()
                                }


                            }


                        }
                    }

                }
            }

            }
        )
    }
    


