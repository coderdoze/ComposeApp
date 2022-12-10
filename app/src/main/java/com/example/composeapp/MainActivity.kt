package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.ui.theme.ComposeAppTheme
import com.example.composeapp.ui.theme.Neon
import com.example.composeapp.ui.theme.ShadowGrey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var contentSize by remember {
                mutableStateOf(200.dp)
            }

            val size by animateDpAsState(
                targetValue = contentSize,
                animationSpec = tween(delayMillis = 300,
                    durationMillis = 3000,
                    easing = LinearOutSlowInEasing
                )
            )

            val infiniteTransition = rememberInfiniteTransition()

            val color by infiniteTransition.animateColor(
                initialValue = Color.Red,
                targetValue = Color.Green,
                animationSpec = infiniteRepeatable(
                    tween(2000),
                repeatMode = RepeatMode.Reverse)
            )

            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                messageBox("TEST MSG",size,color, updatePixels = {it
                    contentSize += it
                })

            }
        }
    }
}

@Composable
fun messageBox(text:String="HELLO",contentSize:Dp,color: Color,updatePixels : (Dp) -> Unit){
    Box(
        modifier = Modifier
            .size(contentSize)
            .background(
                color = color,
                shape = RoundedCornerShape(10.dp, 4.dp, 6.dp, 10.dp)
            )
            .border(
                width = 2.dp,
                color = Neon,
                shape = RoundedCornerShape(10.dp, 4.dp, 6.dp, 10.dp)
            ),
            contentAlignment = Alignment.Center


            )
         {
             Column(horizontalAlignment = Alignment.CenterHorizontally) {
                 Text(text = text, color = Neon, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                 Text(
                     text = "$text $text",
                     color = Color.Red,
                     fontSize = 14.sp,
                     fontWeight = FontWeight.Bold
                 )
                 Spacer(modifier = Modifier.height(10.dp))
                 Button(
                     onClick = {
                         updatePixels(20.dp)
                     }) {
                     Text(text = "CLICK ME")
                 }
             }
        }
}