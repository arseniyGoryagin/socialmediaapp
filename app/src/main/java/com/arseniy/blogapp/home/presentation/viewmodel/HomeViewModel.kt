package com.arseniy.blogapp.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.arseniy.blogapp.data.local.enteties.PostEntity
import com.arseniy.blogapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject






@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository, private val pager : Pager<Int, PostEntity>) : ViewModel(){


    val postsPagingFlow = pager.flow.cachedIn(viewModelScope)



}


