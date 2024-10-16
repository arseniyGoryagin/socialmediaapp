package com.arseniy.blogapp.myprofile.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arseniy.blogapp.data.repository.Repository
import com.arseniy.blogapp.user.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


data class MyProfileUiStates(

    val isLoading : Boolean = true,
    val errorMessage : String? = null


)

class MyProfileData(){
    var profile  : User? by mutableStateOf(null)
}


@HiltViewModel
class MyProfileViewModel @Inject constructor(private val repository: Repository)  : ViewModel(){



    var myProfileStates = mutableStateOf(MyProfileUiStates())
    var myProfileData = MyProfileData()


    fun signOut(){
        viewModelScope.launch {
            repository.shouldLogOut()
        }
    }


    fun loadData(){

        myProfileStates.value = myProfileStates.value.copy(isLoading = true)

        try{

            viewModelScope.launch {

                val result  = repository.getMe();


                result.onLeft {

                    println(it.localizedMessage)

                    myProfileStates.value = myProfileStates.value.copy(isLoading = false, errorMessage = it.localizedMessage)
                    return@launch



                }.onRight {

                    println(it)

                    myProfileStates.value = myProfileStates.value.copy(isLoading = false)

                    myProfileData.profile = it

                    return@launch

                }


            }



        }catch(e : Exception){


            myProfileStates.value = myProfileStates.value.copy(isLoading = false, errorMessage = e.localizedMessage)

        }


    }


}