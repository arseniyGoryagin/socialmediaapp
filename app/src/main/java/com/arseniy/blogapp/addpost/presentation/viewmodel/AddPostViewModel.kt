package com.arseniy.blogapp.addpost.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImagePainter

import com.arseniy.blogapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



data class AddPostUiStates(
    val isLoading : Boolean = false,
    val error : String? = null,
    val isSuccess : Boolean = false
)


class AddPostInputState(){
    var postBody by mutableStateOf("")
}

sealed interface AddPostUiEvent{

    data class PostBodyChanged(val newPostBody : String) : AddPostUiEvent

}

@HiltViewModel
class AddPostViewModel @Inject constructor(private val repository: Repository) : ViewModel(){


    var addPostState by mutableStateOf(AddPostUiStates())
    var addPostInputState by mutableStateOf(AddPostInputState())





    fun onUiEvent(event : AddPostUiEvent){
        when(event){
            is AddPostUiEvent.PostBodyChanged -> addPostInputState.postBody = event.newPostBody
        }

    }

    fun addPost(){

        viewModelScope.launch {

            println("Loading started")

            addPostState = addPostState.copy(isLoading = true)

            val response = repository.addPost(addPostInputState.postBody)

            response.onRight {
                addPostState = addPostState.copy(isLoading = false, isSuccess = true )
            }.onLeft {
                addPostState = addPostState.copy(isLoading = false, error = it.message)
            }


        }


    }


}