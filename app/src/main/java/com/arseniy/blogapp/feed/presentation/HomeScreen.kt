package com.arseniy.blogapp.feed.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadType
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.arseniy.blogapp.Routes
import com.arseniy.blogapp.feed.presentation.componets.HomeTopBar
import com.arseniy.blogapp.feed.presentation.componets.Post
import com.arseniy.blogapp.feed.presentation.viewmodel.HomeViewModel
import com.arseniy.blogapp.ui.theme.Grey222
import com.arseniy.blogapp.util.components.MainBottomBar


@Composable
fun HomeScreen( homeViewModel: HomeViewModel, onNotificationClick : () -> Unit, onAddPostClick : () -> Unit ){


        val posts = homeViewModel.postsPagingFlow.collectAsLazyPagingItems()


        Scaffold(
                topBar = {
                        HomeTopBar(
                                onProfileClick = { /*TODO*/ },
                                onSearch = {},
                                onSettingsClicked = { /*TODO*/ },
                                searchValue = ""
                        )
                },
                content = { innerPadding ->


                        Box (modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)){
                                if(posts.loadState.refresh is LoadState.Loading){
                                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color.Black)
                                }
                                else if(posts.itemCount == 0){
                                        Text(text = "No posts yet!", color = Grey222, modifier = Modifier.align(Alignment.Center))
                                }
                                else{

                                        LazyColumn(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.spacedBy(22.dp),
                                                modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(horizontal = 12.dp)
                                        ) {
                                                items(posts){ post ->

                                                        if(post != null) {

                                                                Post(
                                                                        name = post.username,
                                                                        body = post.body,
                                                                        likes = post.likes,
                                                                        liked = false,
                                                                        modifier = Modifier.fillMaxSize()
                                                                )
                                                        }

                                                }


                                                item {

                                                        if(posts.loadState.append is LoadState.Loading){ CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color.Black)
                                                        }


                                                }

                                        }


                                }

                                MainBottomBar(
                                        modifier = Modifier.padding(horizontal = 32.dp).padding(bottom = 32.dp).align(Alignment.BottomCenter),
                                        onHomeClick = {},
                                        onNotificationsClick = onNotificationClick,
                                        onNewPostClick = onAddPostClick,
                                        currentRoute = Routes.Home
                                )



                        }




                },

        )


}