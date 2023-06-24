package com.example.moneycounter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneycounter.ui.theme.MoneyCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyCounterTheme {
                // A surface container using the 'background' color from the theme
                myApp()
            }
        }
    }
}
@Composable
fun myApp(){
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = Color(0xFF546E7A)
    ){

    }
}
@Preview
@Composable
fun CreateCircle(){
    Card(modifier = Modifier
        .padding(3.dp)
        .size(50.dp)
        .clickable {
            Log.d("Tap", "CreateCircle: ")
        }
    , shape = CircleShape){
        //Align content to center
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(text = "Tap", modifier = Modifier.padding(5.dp))
        }

    }
}



@Preview(showBackground = true)
@Composable
fun defaultPreview() {
    MoneyCounterTheme {
    myApp()
    }
}
