package com.example.moneycounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneycounter.ui.theme.MoneyCounterTheme
import kotlin.math.absoluteValue

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
    var moneyCounter by remember {
        mutableStateOf(0)
    }
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = Color(0xFF546E7A)
    ){
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text="$ ${moneyCounter.absoluteValue}", style = TextStyle(color = Color.White, fontSize = 30.sp,
                fontWeight = FontWeight.Bold))
            //Space
            Spacer(modifier =Modifier.height(130.dp))
            CreateCircle(moneyCounter=moneyCounter.absoluteValue){newValue ->
                moneyCounter = newValue
            }
            if(moneyCounter.absoluteValue>25){
                Text(text = "Lots Of Money!")
            }
        }

    }
}
//Composable Function should not hold any state

//@Preview
@Composable
fun CreateCircle(moneyCounter : Int = 0,updateMoneyCounter: (Int) -> Unit ){

    //varmoneyC  = remeber{
    // mutableStateOf(0) It allows to Hold a value in the memory
    // }
    //Or
    // state variable to keep track of the number of times the circle is clicked
    Card(modifier = Modifier
        .padding(3.dp)
        .size(105.dp)
        .clickable {
            updateMoneyCounter(moneyCounter + 1)
//            moneyCounter += 1
            //moneyC.value += 1
        },shape = CircleShape,elevation = CardDefaults.cardElevation(
        defaultElevation = 10.dp
    )  ){
        //Align content to center
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(text = "Tap $moneyCounter", modifier = Modifier.padding(5.dp))
        }
        // in order to see change in composable we need to redraw it.
        // To make it happen we use state. It will recompose the composable
        // whenever the state changes. Ui will be updated.

    }
}


@Preview(showBackground = true)
@Composable
fun defaultPreview() {
    MoneyCounterTheme {
        myApp()
    }
}
