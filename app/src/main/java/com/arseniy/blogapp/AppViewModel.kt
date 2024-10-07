package com.arseniy.blogapp

import androidx.lifecycle.ViewModel
import com.arseniy.blogapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor  (private val repository: Repository) : ViewModel(){

    val token = repository.getTokenFlow()



}