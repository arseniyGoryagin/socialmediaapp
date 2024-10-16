package com.arseniy.blogapp.profile.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arseniy.blogapp.data.local.db.Db
import com.arseniy.blogapp.data.local.enteties.PostEntity
import com.arseniy.blogapp.data.remote.mediators.PostRemoteMediator
import com.arseniy.blogapp.data.remote.mediators.UserPostRemoteMediator
import com.arseniy.blogapp.data.repository.Repository
import com.arseniy.blogapp.domain.model.Post
import com.arseniy.blogapp.user.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ProfileUiState(


    val isLoadingUser : Boolean = true,
    val isLoadingPosts : Boolean = true,

    val errorMessage : String? = null

)


class ProfileUiData(){
    var userProfile  : User? by mutableStateOf(null)
    val userPosts : List<Post>? by mutableStateOf(null)
}


@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: Repository, private val db : Db): ViewModel(){

    val profileUiData = ProfileUiData()
    val profileUiState = mutableStateOf(ProfileUiState())

    @OptIn(ExperimentalPagingApi::class)
    var postsFlow : Flow<PagingData<PostEntity>>? = null

    private suspend fun fetchUserProfile(username : String ) : User{

        val result = repository.getUser(username)

        return result.fold(
            {
                throw it
            }, {
                it
            }
        )

    }




    @OptIn(ExperimentalPagingApi::class)
    fun loadProfileData(username : String){


        viewModelScope.launch {


            profileUiState.value = profileUiState.value.copy(isLoadingPosts = true, isLoadingUser = true)


            try {

                val  userProfile = fetchUserProfile(username)

                profileUiData.userProfile = userProfile

                postsFlow = Pager(
                    config = PagingConfig(
                        pageSize = 20,
                        prefetchDistance = 0),
                    remoteMediator = UserPostRemoteMediator(
                        repository,
                        db,
                        username = username
                    ),
                    pagingSourceFactory = {
                        repository.getPostPagingSource("user")
                    }
                ).flow.cachedIn(viewModelScope)


                profileUiState.value = profileUiState.value.copy(isLoadingPosts = false, isLoadingUser = false)


            }catch (e : Exception){
                profileUiState.value = profileUiState.value.copy(isLoadingPosts = false, isLoadingUser = false, errorMessage = e.localizedMessage)

            }




        }


    }


}