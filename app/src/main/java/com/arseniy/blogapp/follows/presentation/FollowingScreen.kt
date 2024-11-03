package com.arseniy.blogapp.follows.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.arseniy.blogapp.follows.presentation.viewmodels.FollowsViewModel
import com.arseniy.blogapp.util.components.CenterLoadingCircle
import com.arseniy.blogapp.util.components.ErrorWithRefresh
import com.arseniy.blogapp.util.components.Profile.ProfileTeaser


@Composable
fun FollowsScreen(onBackClick : () -> Unit, onAccountClick : (String) -> Unit,  onRefresh : () -> Unit, followsViewModel : FollowsViewModel){


    val usersFlow = followsViewModel.usersStream.collectAsLazyPagingItems()

    Scaffold(

        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Icon(Icons.Filled.ArrowBack, contentDescription = "Go back", modifier = Modifier.clickable { onBackClick() })
                Text(text = "Follows")
            }
        },

        content = { innerPadding ->



            if(usersFlow.loadState.refresh is LoadState.Loading){
                CenterLoadingCircle()
            }else if(usersFlow.loadState.refresh is LoadState.Error){
                ErrorWithRefresh(onRefreshClick = onRefresh, errorMessage = (usersFlow.loadState.refresh as LoadState.Error).error.localizedMessage ?:"")
            }else {

                LazyColumn(
                    modifier = Modifier.padding(innerPadding).fillMaxSize()
                ) {


                    items(usersFlow) { account ->


                        account?.let {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onAccountClick(account.username) }
                            ) {
                                ProfileTeaser(
                                    name = account.name,
                                    username = account.username,
                                    profilePicture = account.profilePicture,
                                    modifier = Modifier
                                        .padding(horizontal = 36.dp)
                                        .fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                HorizontalDivider(modifier = Modifier.height(2.dp))

                                Spacer(modifier = Modifier.height(36.dp))
                            }
                        }





                    }

                    item{
                        if(usersFlow.loadState.append is LoadState.Loading)
                            CenterLoadingCircle()
                    }

                    item{
                        if(usersFlow.loadState.append is LoadState.Error)
                            ErrorWithRefresh(onRefreshClick = onRefresh, errorMessage = (usersFlow.loadState.append as LoadState.Error).error.localizedMessage ?:"")
                    }


                }
            }

        }
    )


}