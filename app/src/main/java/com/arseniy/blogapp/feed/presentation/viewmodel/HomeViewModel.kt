package com.arseniy.blogapp.feed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.arseniy.blogapp.data.local.enteties.PostEntity
import com.arseniy.blogapp.data.remote.mediators.PostRemoteMediator
import com.arseniy.blogapp.data.repository.Repository
import com.arseniy.blogapp.feed.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject






@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository, private val pager : Pager<Int, PostEntity>) : ViewModel(){


    val postsPagingFlow = pager.flow.cachedIn(viewModelScope)



}


