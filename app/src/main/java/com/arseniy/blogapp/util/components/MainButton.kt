package com.arseniy.blogapp.util.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arseniy.blogapp.ui.theme.Yellow70

@Composable
fun MainButton(text : String, onClick : () -> Unit,  modifier: Modifier = Modifier, isLoading : Boolean = false){
    
    Button(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Yellow70
        ),
        onClick = {onClick()}
    ) {
        if(isLoading){
            Box{
                CircularProgressIndicator(
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }else{
            Text(
                fontSize = 14.sp,
                text = text,
                modifier = Modifier.padding(vertical = 10 .dp),
                fontWeight = FontWeight.Bold)
        }
    }
    
}