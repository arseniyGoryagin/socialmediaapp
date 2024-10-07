package com.arseniy.blogapp.util.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arseniy.blogapp.ui.theme.Grey10


@Composable
@Stable
fun MainTextInput(
    name: String,
                  onChange : (String) -> Unit,
                  value : String,
                  modifier: Modifier = Modifier,
                    hideInput : Boolean =false,
                  placeHolder : @Composable () -> Unit = {},
                  label : @Composable () -> Unit = {},
                  trailingIcon :  @Composable () -> Unit = {},
                  ) {


    Column(
        modifier = modifier,
    ) {
        Text(text = name, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(9.dp))

        TextField(
            shape = RoundedCornerShape(16.dp),
            visualTransformation = if(!hideInput) VisualTransformation.None else PasswordVisualTransformation(),

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
            placeholder = placeHolder,
            label = label,
            trailingIcon = trailingIcon,
            value = value,
            onValueChange = {
                onChange(it)
            },
            modifier = Modifier.fillMaxWidth()
        )

    }
}