package com.arseniy.blogapp.follows.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import arrow.core.invalid
import com.arseniy.blogapp.data.repository.Repository
import com.arseniy.blogapp.search.presentation.viewmodels.SearchViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel(assistedFactory = FollowingViewModel.FollowsViewModelFactory::class)
class FollowingViewModel @AssistedInject constructor(private val repository: Repository, @Assisted private  val username : String): ViewModel(){


   val usersStream = repository.getUserFollowsStream(username).cachedIn(viewModelScope)

    fun refresh(){
        viewModelScope.launch {
        }
    }

    @AssistedFactory
    interface FollowsViewModelFactory{
        fun create(username : String) : FollowingViewModel
    }


}