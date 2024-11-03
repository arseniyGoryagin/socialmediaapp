package com.arseniy.blogapp.editprofile

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SignOutButton(onSignOutClick : () -> Unit, modifier: Modifier = Modifier) {

    Button(
        modifier = modifier,
        onClick = onSignOutClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = "Sign Out", color= Color.Red, fontSize = 14.sp)
    }
}