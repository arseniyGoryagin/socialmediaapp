package com.arseniy.blogapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.arseniy.blogapp.addpost.presentation.AddPostScreen
import com.arseniy.blogapp.addpost.presentation.viewmodel.AddPostViewModel
import com.arseniy.blogapp.auth.presentation.LoginScreen
import com.arseniy.blogapp.auth.presentation.RegisterScreen
import com.arseniy.blogapp.auth.presentation.WelcomeScreen
import com.arseniy.blogapp.auth.presentation.viewmodels.LoginViewModel
import com.arseniy.blogapp.auth.presentation.viewmodels.RegisterViewModel
import com.arseniy.blogapp.feed.presentation.HomeScreen
import com.arseniy.blogapp.feed.presentation.viewmodel.HomeViewModel
import com.arseniy.blogapp.notifications.presentation.NotificationsScreen
import com.arseniy.blogapp.util.components.MainBottomBar
import kotlinx.coroutines.flow.collectLatest


object Routes{


        const val Home = "Home"
        const val NewPost = "NewPost"
        const val Auth = "Auth"
        const val Main = "Main"
        const val Welcome = "Welcome"
        const val Register = "Register"
        const val Login= "Login"
        const val Notifications = "Notifications"


}

@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun App(){



        val navController = rememberNavController()

        val appViewModel : AppViewModel = hiltViewModel()

        LaunchedEffect(appViewModel.token){
                appViewModel.token.collectLatest { token ->

                        if(token == ""){
                                navController.navigate(Routes.Auth){
                                        popUpTo(Routes.Welcome){
                                                inclusive = true
                                        }
                                }
                        }
                }
        }


        val currentBackStackEntry by navController.currentBackStackEntryAsState()

        val currentDestination  = currentBackStackEntry?.destination?.route


        Scaffold(



                content = { innerPadding ->


                        NavHost(navController = navController,
                                startDestination = Routes.Main  ,
                                modifier = Modifier.padding(innerPadding)){
                               // enterTransition = { EnterTransition.None},
                              //  exitTransition = { ExitTransition.None}){


                        navigation(route = Routes.Auth,  startDestination = Routes.Welcome) {

                                composable(Routes.Welcome) {

                                        val onLoginClick = {

                                                navController.navigate(Routes.Login)


                                        }

                                        WelcomeScreen(onSignUpClick = {
                                                navController.navigate(Routes.Register)
                                        }, onLoginClick = onLoginClick)
                                }
                                composable(Routes.Login){


                                        val loginViewModel = hiltViewModel<LoginViewModel>()


                                        val onBackClick = {
                                                navController.navigate(Routes.Welcome){
                                                        popUpTo(Routes.Welcome){}
                                                }
                                        }


                                        LaunchedEffect(loginViewModel.loginUiState) {
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

                                composable(Routes.Register) {

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




                        navigation( route = Routes.Main, startDestination = Routes.Home){

                                composable(Routes.Home) {

                                        val homeViewModel : HomeViewModel= hiltViewModel()

                                        HomeScreen (homeViewModel= homeViewModel, onNotificationClick = {
                                                navController.navigate(Routes.Notifications) {
                                                        popUpTo(Routes.Notifications) {
                                                                inclusive = true
                                                        }
                                                }
                                        }, onAddPostClick = {
                                                navController.navigate(Routes.NewPost)
                                        })
                                }

                                composable(Routes.Notifications) {
                                        NotificationsScreen(
                                                onHomeClick = { navController.navigate(Routes.Home) {
                                                popUpTo(Routes.Home) {
                                                        inclusive = true
                                                }
                                        }}, onAddPostClick = {

                                                        navController.navigate(Routes.NewPost)

                                                })
                                }


                                composable(Routes.NewPost){


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

                bottomBar = {
                        currentDestination?.let {

                                val bottomBarRoutes = listOf(
                                        Routes.Home,
                                        Routes.Notifications
                                )


                                if (currentDestination in bottomBarRoutes) {


                                        println("CURRRENT DESTIONATIONS ++++ " + currentDestination)


                                        /*MainBottomBar(
                                                modifier = Modifier.padding(horizontal = 32.dp).padding(bottom = 32.dp),
                                                onHomeClick = {
                                                        navController.navigate(Routes.Home) {
                                                                popUpTo(Routes.Home) {
                                                                        inclusive = true
                                                                }
                                                        }
                                                },
                                                onNotificationsClick = {
                                                        navController.navigate(Routes.Notifications) {
                                                                popUpTo(Routes.Notifications) {
                                                                        inclusive = true
                                                                }
                                                        }
                                                },
                                                onNewPostClick = {
                                                        navController.navigate(Routes.NewPost)
                                                },
                                                currentRoute = currentDestination
                                        )*/
                                }
                        }

                }

        )




}
