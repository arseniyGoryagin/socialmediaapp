package com.arseniy.blogapp.search.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.arseniy.blogapp.data.local.db.Db
import com.arseniy.blogapp.data.local.enteties.UserEntity
import com.arseniy.blogapp.data.remote.mediators.UserPostRemoteMediator
import com.arseniy.blogapp.data.remote.mediators.UserRemoteMediator
import com.arseniy.blogapp.data.repository.Repository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject





sealed interface UiEvent{
    data class SearchChanged(val newSearch : String) : UiEvent
}



@HiltViewModel(assistedFactory = SearchViewModel.SearchViewModelFactory::class)
class SearchViewModel @AssistedInject constructor(
    private val repository: Repository,
    private val db : Db,
    @Assisted private val searchString : String ): ViewModel() {

    val currentSearch = mutableStateOf(searchString)

    @OptIn(ExperimentalPagingApi::class)
    var usersFlow = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 0),
        remoteMediator = UserRemoteMediator(
            repository,
            db,
            searchString = searchString
        ),
        pagingSourceFactory = {
            repository.getUsersSource("search")
        }
    ).flow.cachedIn(viewModelScope)



    @AssistedFactory
    interface SearchViewModelFactory{
        fun create(searchString : String) : SearchViewModel
    }


    @OptIn(ExperimentalPagingApi::class)
    private fun updateSearch(searchString : String){

         usersFlow = Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 0),
            remoteMediator = UserRemoteMediator(
                repository,
                db,
                searchString = searchString
            ),
            pagingSourceFactory = {
                repository.getUsersSource("search")
            }
        ).flow.cachedIn(viewModelScope)
    }



    fun onUiEvent(event : UiEvent){
        when(event){
            is UiEvent.SearchChanged -> {
                currentSearch.value = event.newSearch
                updateSearch(event.newSearch)
            }
        }
    }


}