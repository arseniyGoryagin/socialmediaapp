package com.arseniy.blogapp.auth.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arseniy.blogapp.util.components.MainButton
import com.arseniy.blogapp.util.components.MainTextInput
import com.arseniy.blogapp.ui.theme.Grey10
import com.arseniy.blogapp.ui.theme.Grey222
import com.arseniy.blogapp.R
import com.arseniy.blogapp.auth.presentation.viewmodels.LoginUiEvent
import com.arseniy.blogapp.auth.presentation.viewmodels.LoginViewModel
import com.arseniy.blogapp.auth.presentation.viewmodels.RegisterUiEvent
import com.arseniy.blogapp.util.components.BottomErrorMessage


@Composable
fun  LoginScreen(onBackClick : () -> Unit, loginViewModel: LoginViewModel) {


    val state by remember {
        loginViewModel.loginUiState
    }


    val inputState by remember {
        loginViewModel.loginInputState
    }

    val scroll = rememberScrollState()

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
            .padding(top = 13.dp)
            .verticalScroll(scroll)
    ){




        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){

            Icon(
                Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Go back",
                modifier = Modifier.clickable { onBackClick() })

            Spacer(modifier = Modifier.width(12.dp))

            Text(text = "Sign In", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }


        Spacer(modifier = Modifier.height(24.dp))


        MainTextInput(
            value = inputState.usernameInput,
            modifier = Modifier.fillMaxWidth(),
            name = "Username",
            onChange = {newValue -> loginViewModel.onLoginUiEvent(LoginUiEvent.UsernameChanged(newValue))}
        )


        Spacer(modifier = Modifier.height(12.dp))


        MainTextInput(
            value = inputState.passwordInput,
            modifier = Modifier.fillMaxWidth(),
            name = "Password",
            trailingIcon = {
                Icon(painter = painterResource(id = if (inputState.showPassword){R.drawable.eye_outline}else{R.drawable.eye_off_outline}),
                    contentDescription = "show or hide password",
                    Modifier.clickable { loginViewModel.onLoginUiEvent(LoginUiEvent.ShowPasswordChanged(!inputState.showPassword))})
            },
            onChange = {newValue -> loginViewModel.onLoginUiEvent(LoginUiEvent.PasswordChanged(newValue))}
        )




        Spacer(modifier = Modifier.height(16.dp))




        Spacer(modifier = Modifier.height(16.dp))


        MainButton(
            text = "Sign In",
            onClick = { loginViewModel.login()}
            , modifier = Modifier.fillMaxWidth()
        )

        val errorMessage = state.errorMessage
        if( errorMessage  != null){
            Spacer(modifier = Modifier.height(12.dp))
            BottomErrorMessage(message = errorMessage, modifier = Modifier.fillMaxWidth())
        }


    }


}


/*

@Preview
@Composable
fun  PrevLogin() {


    LoginScreenContent(onBackClick = onBackClick,onSignInClick = onSignInClick )
}*/