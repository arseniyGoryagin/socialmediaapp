package com.arseniy.blogapp.util.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arseniy.blogapp.ui.theme.Grey10

@Composable
@Stable
fun SearchBar(
    onChange : (String) -> Unit,
    value : String,
    modifier: Modifier = Modifier,
) {



        TextField(

            shape = RoundedCornerShape(16.dp),

            singleLine = true,

            colors = TextFieldDefaults.colors(

                disabledContainerColor = Grey10,
                focusedContainerColor = Grey10,
                unfocusedContainerColor = Grey10,
                errorContainerColor = Grey10,


                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,

                cursorColor = Color.Black,

                disabledTextColor = Color.Black,
                errorTextColor = Color.Black,
                focusedTextColor = Color.Black,

                disabledSupportingTextColor = Color.Black,
                errorSupportingTextColor = Color.Black,
                focusedSupportingTextColor = Color.Black,


                ),
            placeholder = { Text(text = "Search something...")},
            leadingIcon = {
                Icon(Icons.Filled.Search, contentDescription = null )
            },
            value = value,
            onValueChange = {
                onChange(it)
            },
            modifier = modifier
        )
}