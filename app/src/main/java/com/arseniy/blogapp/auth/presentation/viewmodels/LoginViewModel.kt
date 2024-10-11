package com.arseniy.blogapp.auth.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arseniy.blogapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


data class LoginUiState(
    val isLoading: Boolean = false,
    val errorMessage : String? = null,
    val isErrorRead : Boolean = true,
    val isLoginSuccess : Boolean = false

    )



class LoginInputState(){
    var usernameInput by mutableStateOf("")
    var passwordInput by mutableStateOf("")
    var showPassword by mutableStateOf(false)
}



sealed interface LoginUiEvent{
    data class UsernameChanged(val newValue : String) : LoginUiEvent
    data class PasswordChanged(val newValue: String) : LoginUiEvent
    data class ShowPasswordChanged(val newValue : Boolean) : LoginUiEvent
}

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository)  : ViewModel(){


    var loginUiState = mutableStateOf(LoginUiState())
    var loginInputState = mutableStateOf(LoginInputState())



    fun onLoginUiEvent(event : LoginUiEvent){

       when(event){
           is LoginUiEvent.PasswordChanged -> loginInputState.value .passwordInput = event.newValue
           is LoginUiEvent.ShowPasswordChanged -> loginInputState.value .showPassword= event.newValue
           is LoginUiEvent.UsernameChanged -> loginInputState.value.usernameInput = event.newValue
       }

    }



    fun login(){

        println("loginged in!!!")

        viewModelScope.launch {

            loginUiState.value = loginUiState.value.copy(isLoading = true)

            try {

                val result = repository.login(
                    loginInputState.value.usernameInput,
                    loginInputState.value.passwordInput
                )

                result.onLeft {

                    loginUiState.value = loginUiState.value.copy(
                        isLoading = false,
                        isLoginSuccess = false,
                        isErrorRead = false,
                        errorMessage = it.message
                    )

                }.onRight {

                    loginUiState.value = loginUiState.value.copy(isLoading = false, isLoginSuccess = true)
                }
            }catch (e : Exception){

                loginUiState.value = loginUiState.value.copy(
                    isLoading = false,
                    isLoginSuccess = false,
                    isErrorRead = false,
                    errorMessage = e.message
                )

            }


        }


    }




}