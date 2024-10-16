package com.arseniy.blogapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.arseniy.blogapp.addpost.presentation.AddPostScreen
import com.arseniy.blogapp.addpost.presentation.viewmodel.AddPostViewModel
import com.arseniy.blogapp.auth.presentation.LoginScreen
import com.arseniy.blogapp.auth.presentation.RegisterScreen
import com.arseniy.blogapp.auth.presentation.WelcomeScreen
import com.arseniy.blogapp.auth.presentation.viewmodels.LoginViewModel
import com.arseniy.blogapp.auth.presentation.viewmodels.RegisterViewModel
import com.arseniy.blogapp.home.presentation.HomeScreen
import com.arseniy.blogapp.home.presentation.viewmodel.HomeViewModel
import com.arseniy.blogapp.myprofile.presentation.MyProfileScreen
import com.arseniy.blogapp.myprofile.presentation.viewmodels.MyProfileViewModel
import com.arseniy.blogapp.navigation.Routes
import com.arseniy.blogapp.notifications.presentation.NotificationsScreen
import com.arseniy.blogapp.profile.presentation.ProfileScreen
import com.arseniy.blogapp.profile.presentation.viewmodels.ProfileViewModel
import com.arseniy.blogapp.search.presentation.SearchScreen
import com.arseniy.blogapp.search.presentation.viewmodels.SearchViewModel
import com.arseniy.blogapp.search.presentation.viewmodels.UiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first


@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun App(){



        val navController = rememberNavController()

        val appViewModel : AppViewModel = hiltViewModel()

        val logOutState =appViewModel.shouldLogOut

        LaunchedEffect(logOutState){


                appViewModel.shouldLogOut.collectLatest { state ->

                        if(state == true){
                                navController.navigate(Routes.Auth){
                                        popUpTo(Routes.Welcome){
                                                inclusive = true
                                        }
                                }

                                appViewModel.logOut()
                        }
                }
        }



        Scaffold(



                content = { innerPadding ->


                        NavHost(navController = navController,
                                startDestination = Routes.Main  ,
                                modifier = Modifier.padding(innerPadding)){
                               // enterTransition = { EnterTransition.None},
                              //  exitTransition = { ExitTransition.None}){


                        navigation<Routes.Auth>(startDestination = Routes.Welcome) {

                                composable<Routes.Welcome> {

                                        val onLoginClick = {

                                                navController.navigate(Routes.Login)


                                        }

                                        WelcomeScreen(onSignUpClick = {
                                                navController.navigate(Routes.Register)
                                        }, onLoginClick = onLoginClick)
                                }
                                composable<Routes.Login>{


                                        val loginViewModel = hiltViewModel<LoginViewModel>()


                                        val onBackClick = {
                                                navController.navigate(Routes.Welcome){
                                                        popUpTo(Routes.Welcome){}
                                                }
                                        }


                                        LaunchedEffect(loginViewModel.loginUiState.value) {
                                                if(loginViewModel.loginUiState.value.isLoginSuccess){
                                                        navController.navigate(Routes.Main){
                                                                popUpTo(Routes.Welcome){
                                                                        inclusive = true
                                                                }
                                                        }
                                                }
                                        }


                                        LoginScreen(onBackClick = onBackClick, loginViewModel = loginViewModel)
                                }

                                composable<Routes.Register> {

                                        val registerViewModel = hiltViewModel<RegisterViewModel>()



                                        val onBackClick = {
                                                navController.navigate(Routes.Welcome)
                                        }

                                        LaunchedEffect(registerViewModel.registerState) {
                                                if(registerViewModel.registerState.isRegisterSuccess){
                                                        navController.navigate(Routes.Main){
                                                                popUpTo(Routes.Main){
                                                                        inclusive = true
                                                                }
                                                        }
                                                }
                                        }

                                        RegisterScreen(onBackClick = onBackClick, registerViewModel = registerViewModel )

                                }
                        }




                        navigation<Routes.Main>(startDestination = Routes.Home){

                                composable<Routes.Home> {

                                        val homeViewModel : HomeViewModel= hiltViewModel()

                                        HomeScreen (
                                                homeViewModel= homeViewModel,
                                                onNotificationClick = {
                                                navController.navigate(Routes.Notifications) {
                                                        popUpTo(Routes.Notifications) {
                                                                inclusive = true
                                                        }
                                                }
                                                },
                                                onAddPostClick = {
                                                navController.navigate(Routes.AddPost)
                                                },
                                                onUsernameClick = { username ->
                                                        navController.navigate(Routes.Profile(username))
                                                },

                                                onProfileClick = {
                                                        navController.navigate(Routes.MyProfile)
                                                },
                                                onSearchClick = {
                                                        navController.navigate(Routes.Search(""))
                                                }
                                                )
                                }


                                composable<Routes.Profile> { backStackEntry ->

                                        val profileRoute : Routes.Profile = backStackEntry.toRoute<Routes.Profile>()

                                        val profileViewModel : ProfileViewModel = hiltViewModel()


                                        LaunchedEffect(profileRoute.username) {
                                                profileViewModel.loadProfileData(profileRoute.username)
                                        }


                                        ProfileScreen(
                                                profileViewModel = profileViewModel,
                                                onBackClick = {
                                                navController.navigateUp()
                                                },
                                                onRetryClick = {
                                                        profileViewModel.loadProfileData(profileRoute.username)
                                                }
                                        )
                                }

                                composable<Routes.Search> {  backStackEntry ->


                                        val searchString = backStackEntry.toRoute<Routes.Search>().searchString

                                        val searchViewModel = hiltViewModel<SearchViewModel, SearchViewModel.SearchViewModelFactory> { factory ->
                                                factory.create(searchString)
                                        }


                                        SearchScreen(
                                                searchViewModel = searchViewModel,
                                                onAccountClick = { username ->
                                                        navController.navigate(Routes.Profile(username))
                                                },
                                                onRefresh = {},
                                                onSearchChange = { search ->
                                                       searchViewModel.onUiEvent(UiEvent.SearchChanged(search))
                                                },
                                                onBackClick = {
                                                        navController.navigateUp()
                                                }
                                        )
                                }

                                composable<Routes.Notifications> {
                                        NotificationsScreen(
                                                onHomeClick = { navController.navigate(Routes.Home) {
                                                popUpTo(Routes.Home) {
                                                        inclusive = true
                                                }
                                        }}, onAddPostClick = {

                                                        navController.navigate(Routes.AddPost)

                                                })
                                }


                                composable<Routes.MyProfile> {

                                        val myProfileViewModel : MyProfileViewModel = hiltViewModel()

                                        LaunchedEffect(Unit){
                                                myProfileViewModel.loadData()
                                        }

                                        MyProfileScreen(
                                                onBackClick = {navController.navigateUp() },
                                                onRefresh = {myProfileViewModel.loadData()},
                                                onSignOut = {
                                                        myProfileViewModel.signOut()
                                                            },
                                                myProfileViewModel = myProfileViewModel,
                                                onProfileClick = { username->
                                                        navController.navigate(Routes.Profile(username))
                                                }
                                        )
                                }


                                composable<Routes.AddPost>{


                                        val addPostViewModel : AddPostViewModel = hiltViewModel()


                                        val onClose ={
                                                navController.navigate(Routes.Home){
                                                        popUpTo(Routes.Home){
                                                                inclusive = true
                                                        }
                                                }
                                                Unit
                                        }



                                        LaunchedEffect(addPostViewModel.addPostState) {
                                                if(addPostViewModel.addPostState.isSuccess == true){
                                                        onClose()
                                                }
                                        }


                                        AddPostScreen(onCloseClick = onClose, addPostViewModel = addPostViewModel)
                                }


                        }



                }
                },


        )




}
