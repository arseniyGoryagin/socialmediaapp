package com.arseniy.blogapp.auth.presentation

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arseniy.blogapp.util.components.MainButton
import com.arseniy.blogapp.ui.theme.Grey10
import com.arseniy.blogapp.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(onSignUpClick : () -> Unit , onLoginClick: () -> Unit ){



    val pagerState = rememberPagerState(pageCount = {3});
    val scroll = rememberScrollState()



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Grey10)
            .verticalScroll(scroll)

    ) {



        Image(
            modifier = Modifier.weight(0.5F).fillMaxSize().padding(horizontal = 40.dp   ),
            painter = painterResource(id = R.drawable.messages), contentDescription = "Messages",
            )



        Box(
            modifier = Modifier
                .weight(0.5F)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)

        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)

            ){
                Spacer(modifier = Modifier.height(32.dp))

                Text(text = "Stay connected with your loved ones", fontSize = 34.sp, textAlign = TextAlign.Center)

                Spacer(modifier = Modifier.height(24.dp))


                MainButton(
                    onClick = onSignUpClick,
                    text = "Sign up with phone or email",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(34.dp))



                Text(
                    text = AnnotatedString.Builder("Don’t have an account? ").apply {
                    append("Sign in")
                        addStyle(style = SpanStyle(fontWeight = FontWeight.Bold), start = 23, end = length)

                    }.toAnnotatedString(),
                    modifier = Modifier.clickable {  onLoginClick()}
                )

                Spacer(modifier = Modifier.height(54.dp))


            }




        }




    }


}

