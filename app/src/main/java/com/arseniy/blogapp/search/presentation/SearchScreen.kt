package com.arseniy.blogapp.search.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.arseniy.blogapp.search.presentation.viewmodels.SearchViewModel
import com.arseniy.blogapp.util.components.CenterLoadingCircle
import com.arseniy.blogapp.util.components.ErrorWithRefresh
import com.arseniy.blogapp.util.components.ProfileHeader
import com.arseniy.blogapp.util.components.SearchBar


@Composable
fun SearchScreen(onSearchChange : (String) -> Unit, onAccountClick : (String) -> Unit, onBackClick : () -> Unit, onRefresh : () -> Unit, searchViewModel: SearchViewModel) {


    val userAccounts = searchViewModel.usersFlow.collectAsLazyPagingItems()
    val searchString by remember {
        searchViewModel.currentSearch
    }

    Scaffold(

        topBar = {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "go back",
                    Modifier
                        .padding(horizontal = 10.dp)
                        .clickable { onBackClick() })
                SearchBar(
                onChange = onSearchChange,
                value = searchString,
                modifier = Modifier.fillMaxWidth()
            )
        }
        },
        content = { innerPadding ->

            if(userAccounts.loadState.refresh is LoadState.Loading){
                CenterLoadingCircle()
            }else if(userAccounts.loadState.refresh is LoadState.Error){
                ErrorWithRefresh(onRefreshClick = onRefresh, errorMessage = (userAccounts.loadState.refresh as LoadState.Error).error.localizedMessage ?:"")
            }else {

                println("user accounts == " + userAccounts.itemSnapshotList)

                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(vertical = 36.dp, horizontal = 12.dp)
                ) {

                    items(userAccounts) { account ->


                        account?.let {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onAccountClick(account.username) }
                            ){
                                ProfileHeader(
                                    username = account.username,
                                    description = account.description,
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
                        if(userAccounts.loadState.append is LoadState.Loading)
                        CenterLoadingCircle()
                    }

                    item{
                        if(userAccounts.loadState.append is LoadState.Error)
                        ErrorWithRefresh(onRefreshClick = onRefresh, errorMessage = (userAccounts.loadState.append as LoadState.Error).error.localizedMessage ?:"")
                    }

                }
            }

        },


    )



}