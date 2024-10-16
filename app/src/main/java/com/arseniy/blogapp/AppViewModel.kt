package com.arseniy.blogapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arseniy.blogapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor  (private val repository: Repository) : ViewModel(){

    val token = repository.getTokenFlow()
    val shouldLogOut = repository.shouldLogOut

    fun logOut(){
        viewModelScope.launch {
            repository.logOut()
        }
    }


}