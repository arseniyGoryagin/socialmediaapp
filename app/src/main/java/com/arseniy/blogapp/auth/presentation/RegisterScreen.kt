package com.arseniy.blogapp.auth.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arseniy.blogapp.util.components.MainButton
import com.arseniy.blogapp.util.components.MainTextInput
import com.arseniy.blogapp.ui.theme.Grey10
import com.arseniy.blogapp.ui.theme.Grey222
import com.arseniy.blogapp.R
import com.arseniy.blogapp.auth.presentation.viewmodels.RegisterUiEvent
import com.arseniy.blogapp.auth.presentation.viewmodels.RegisterUiState
import com.arseniy.blogapp.auth.presentation.viewmodels.RegisterViewModel
import com.arseniy.blogapp.util.components.BottomErrorMessage


@Composable
fun  RegisterScreen(
    registerViewModel: RegisterViewModel,
    onBackClick : () -> Unit) {


    val state = registerViewModel.registerState

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

            Text(text = "Create Account ", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }


        Spacer(modifier = Modifier.height(24.dp))



        MainTextInput(
            value = registerViewModel.registerInputState.emailInput,
            modifier = Modifier.fillMaxWidth(),
            name = "Email",
            placeHolder = { Text(text = "Enter your email", color = Color.Black)},
            onChange = {newValue -> registerViewModel.onUiEvent(RegisterUiEvent.EmailChange(newValue))}
        )

        Spacer(modifier = Modifier.height(12.dp))

        MainTextInput(
            value = registerViewModel.registerInputState.usernameInput,
            placeHolder = { Text(text = "Enter your username", color = Color.Black)},
            modifier = Modifier.fillMaxWidth(),
            name = "Username",
            onChange = {newValue -> registerViewModel.onUiEvent(RegisterUiEvent.UsernameChange(newValue))}
        )


        Spacer(modifier = Modifier.height(12.dp))


        MainTextInput(
            hideInput = registerViewModel.registerInputState.showPassword,
            value = registerViewModel.registerInputState.passwordInput,
            placeHolder = { Text(text = "Enter your password", color = Color.Black)},
            modifier = Modifier.fillMaxWidth(),
            name = "Password",
            trailingIcon = {
                Icon(painter = painterResource(id = if (registerViewModel.registerInputState.showPassword){R.drawable.eye_outline}else{R.drawable.eye_off_outline}),
                    contentDescription = "show or hide password",
                    Modifier.clickable { registerViewModel.onUiEvent(RegisterUiEvent.ShowPasswordChange(!registerViewModel.registerInputState.showPassword))})
            },
            onChange = {newValue -> registerViewModel.onUiEvent(RegisterUiEvent.PasswordChange(newValue))}
        )

        Spacer(modifier = Modifier.height(12.dp))

        MainTextInput(
            hideInput = registerViewModel.registerInputState.showConfirmPassword,
            value = registerViewModel.registerInputState.confirmPasswordInput,
            modifier = Modifier.fillMaxWidth(),
            placeHolder = {Text("Confirm your password")},
            name = "Confirm Password",
            trailingIcon =  {
                Icon(painter = painterResource(id = if (registerViewModel.registerInputState.showConfirmPassword){R.drawable.eye_outline}else{R.drawable.eye_off_outline}),
                    contentDescription = "show or hide password",
                    Modifier.clickable { registerViewModel.onUiEvent(RegisterUiEvent.ShowConfirmPasswordChange(!registerViewModel.registerInputState.showConfirmPassword)) })
            },
            onChange = {newValue -> registerViewModel.onUiEvent(RegisterUiEvent.ConfirmPasswordChange(newValue))},
        )


        Spacer(modifier = Modifier.height(16.dp))


        Row(
            verticalAlignment = Alignment.Top
        ){
            Checkbox(
                colors = CheckboxDefaults.colors(
                    checkedColor = Grey10,
                    uncheckedColor = Grey10
                ),
                checked = registerViewModel.registerInputState.termsOfServiceChecked,
                onCheckedChange = {
                registerViewModel.onUiEvent(RegisterUiEvent.TermsOfServiceChange(!registerViewModel.registerInputState.termsOfServiceChecked))
            })

            Text(text = "I have read and agree to Sirkell Terms of Service and Privacy Policy ",
                color = Grey222)
        }

        Spacer(modifier = Modifier.height(16.dp))


        MainButton(text = "Create Account",
            onClick = { registerViewModel.register()} ,
            modifier = Modifier.fillMaxWidth(),
            isLoading = state.isLoading
            )


        val errorMessage = state.errorMessage
        if( errorMessage  != null){
            Spacer(modifier = Modifier.height(12.dp))
            BottomErrorMessage(message = errorMessage, modifier = Modifier.fillMaxWidth())
        }

    }


}

