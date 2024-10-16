package com.arseniy.blogapp.myprofile.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arseniy.blogapp.myprofile.components.MyProfileTopBar
import com.arseniy.blogapp.myprofile.components.SignOutButton
import com.arseniy.blogapp.myprofile.presentation.viewmodels.MyProfileViewModel
import com.arseniy.blogapp.profile.presentation.viewmodels.ProfileViewModel
import com.arseniy.blogapp.util.components.CenterLoadingCircle
import com.arseniy.blogapp.util.components.ErrorWithRefresh
import com.arseniy.blogapp.util.components.ProfileHeader


@Composable
fun MyProfileScreen (onBackClick : () -> Unit, myProfileViewModel: MyProfileViewModel, onRefresh : () -> Unit, onSignOut : () -> Unit, onProfileClick : (String) -> Unit){


    val uiState by remember{myProfileViewModel.myProfileStates}
    val uiData = myProfileViewModel.myProfileData



    Scaffold(

        topBar = { MyProfileTopBar (onCloseClick = onBackClick)},
        content = {

            innerPadding ->

            if(uiState.isLoading == true){
                CenterLoadingCircle()
            }
            else if(uiState.errorMessage != null){
                Column(modifier = Modifier.fillMaxSize()){
                    ErrorWithRefresh(onRefreshClick = onRefresh, errorMessage = uiState.errorMessage!!)
                    SignOutButton(onSignOutClick = onSignOut, modifier = Modifier.fillMaxWidth())
                }
            }
            else {
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(36.dp),
                        ) {

                        ProfileHeader(username = uiData.profile?.username, description = "", profilePicture = uiData.profile?.profilePicture,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    uiData.profile?.let {
                                        onProfileClick(it.username)
                                    }
                                })

                        SignOutButton(onSignOutClick = onSignOut, modifier = Modifier.fillMaxWidth())
                    }
                }
            }

        }
    )

}

