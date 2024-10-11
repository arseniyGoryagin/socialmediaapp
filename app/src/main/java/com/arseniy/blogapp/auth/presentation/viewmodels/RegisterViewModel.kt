package com.arseniy.blogapp.auth.presentation.viewmodels

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arseniy.blogapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class  RegisterUiInputState (){

    var usernameInput by mutableStateOf("")
    var emailInput by mutableStateOf("")
    var passwordInput by mutableStateOf("")
    var confirmPasswordInput by mutableStateOf("")
    var termsOfServiceChecked by mutableStateOf(false)
    var showPassword by mutableStateOf(false)
    var showConfirmPassword by mutableStateOf(false)

}

data class RegisterUiState (


    var isLoading : Boolean = false,
    var errorMessage : String? = null,
    var onErrorModified : Boolean = true,
    var isRegisterSuccess : Boolean = false,
)


sealed interface RegisterUiEvent{

    data class  PasswordChange(val newValue : String) : RegisterUiEvent
    data class  UsernameChange(val newValue : String) : RegisterUiEvent
    data class  EmailChange(val newValue : String) : RegisterUiEvent
    data class  ConfirmPasswordChange(val newValue : String) : RegisterUiEvent
    data class  ShowPasswordChange(val newValue : Boolean) : RegisterUiEvent
    data class  TermsOfServiceChange(val newValue : Boolean) : RegisterUiEvent
    data class  ShowConfirmPasswordChange(val newValue : Boolean) : RegisterUiEvent

}


@HiltViewModel
class RegisterViewModel  @Inject constructor(private val repository: Repository) : ViewModel() {



    var registerState : RegisterUiState by mutableStateOf(RegisterUiState())
        private set

    var registerInputState : RegisterUiInputState by mutableStateOf(RegisterUiInputState())
        private set



    fun onUiEvent(event : RegisterUiEvent){

        when(event){
            is RegisterUiEvent.ConfirmPasswordChange -> registerInputState.confirmPasswordInput = event.newValue
            is RegisterUiEvent.EmailChange -> registerInputState.emailInput = event.newValue
            is RegisterUiEvent.PasswordChange -> registerInputState.passwordInput = event.newValue
            is RegisterUiEvent.ShowConfirmPasswordChange -> registerInputState.showConfirmPassword = event.newValue
            is RegisterUiEvent.ShowPasswordChange -> registerInputState.showPassword = event.newValue
            is RegisterUiEvent.TermsOfServiceChange -> registerInputState.termsOfServiceChecked = event.newValue
            is RegisterUiEvent.UsernameChange -> registerInputState.usernameInput = event.newValue
        }

    }


    private fun validate() : Boolean{
        if(registerInputState.confirmPasswordInput != registerInputState.passwordInput){
            registerState = registerState.copy(errorMessage = "Passwords must match!!")
            return false
        }
        if(registerInputState.termsOfServiceChecked == false){
            registerState = registerState.copy(errorMessage = "You must except the terms of service")
        }
        return true
    }


    fun register(){


        if(!validate()){
            return
        }

        viewModelScope.launch {

            registerState = registerState.copy(isLoading = true)

            try {


                val result = repository.register(registerInputState.emailInput, registerInputState.usernameInput, registerInputState.passwordInput);
                result.onLeft{
                    registerState = registerState.copy( errorMessage = it.message,isLoading = false, onErrorModified = false)
                }.onRight { response ->
                    registerState = registerState.copy( isRegisterSuccess = true,isLoading = false, )
                }
            }catch (e : Exception){
                registerState = registerState.copy( errorMessage = e.localizedMessage,isLoading = false, onErrorModified = false)
            }



        }


    }



}