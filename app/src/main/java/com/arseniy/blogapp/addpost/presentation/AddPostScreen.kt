package com.arseniy.blogapp.addpost.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.TestModifierUpdaterLayout
import androidx.compose.ui.unit.dp
import com.arseniy.blogapp.addpost.presentation.components.PostTopBar
import com.arseniy.blogapp.addpost.presentation.viewmodel.AddPostUiEvent
import com.arseniy.blogapp.addpost.presentation.viewmodel.AddPostUiStates
import com.arseniy.blogapp.addpost.presentation.viewmodel.AddPostViewModel
import com.arseniy.blogapp.util.components.MainButton
import retrofit2.http.POST


@Composable
fun AddPostScreen(onCloseClick : () -> Unit, addPostViewModel: AddPostViewModel){

    val state = addPostViewModel.addPostState
    val inputState = addPostViewModel.addPostInputState

    val scroll = rememberScrollState()

    Scaffold(

        topBar = {
            PostTopBar(onCloseClick = onCloseClick)
        },
        content = { innerPadding ->

                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(scroll)
                        .padding(horizontal = 24.dp)
                        .imePadding()

                ){
                    Spacer(modifier = Modifier.height(18.dp))

                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        value = inputState.postBody,
                        onValueChange = {addPostViewModel.onUiEvent(AddPostUiEvent.PostBodyChanged(it))}
                    )

                    if(inputState.postBody.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Icon(
                                Icons.Filled.Clear,
                                contentDescription = null,
                                modifier = Modifier.clickable { addPostViewModel.onUiEvent(AddPostUiEvent.PostBodyChanged(""))}
                                    )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(50.dp))

                    if(state.error != null){
                        Text("Error ${state.error}", color = Color.Red)
                    }


                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        MainButton(
                            text = "Post Now",
                            onClick = { addPostViewModel.addPost() },
                            isLoading = state.isLoading)
                    }

                    Spacer(modifier = Modifier.height(12.dp))





                }


        }


    )

}