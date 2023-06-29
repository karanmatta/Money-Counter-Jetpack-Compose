package com.example.money_counter_jetpack_compose





import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.money_counter_jetpack_compose.components.InputField
import com.example.money_counter_jetpack_compose.ui.theme.MoneyCounterJetpackComposeTheme
import com.example.money_counter_jetpack_compose.util.calculateTotalTip
import com.example.money_counter_jetpack_compose.widgets.RoundIconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // It is a Container Function

            // A surface container using the 'background' color from the theme
            MyApp {
                TopHeader()

            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
   MoneyCounterJetpackComposeTheme() {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }

}


@Composable
fun TopHeader(totalPerPerson : Double = 134.0){
    // Surface Bnaya
    Surface(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(150.dp)
        // .clip is used to give rounded corners to the surface
        .clip(shape = CircleShape.copy(CornerSize(12.dp))),color= Color(0xFFE9D7F7)) {
        //.clip(shape = RoundedCornerShape(corner = CornerSize(12.dp)))) {
        // Align krne k lie ek k neeche ek column bnaya
        Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            val total = "%.2f".format(totalPerPerson)
            Text(text = "Total per Person",
                style = MaterialTheme.typography.headlineMedium)
            Text(text = "$$totalPerPerson",
                style = MaterialTheme.typography.headlineMedium, fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold)

        }
    }
}

// The values inside the main content must be known to all other composable functions

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun MainContent(){
Column {
    TopHeader()
    BillForm() {

            billAmt ->
        Log.d("Amt", "MainContent: $billAmt")
    }
}

}

@Preview
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(modifier: Modifier = Modifier,
onValChange:(String) -> Unit ={} ) {

    val totalBillState = remember {
        mutableStateOf("")
    }
    // State variable to check Valid Input in text field\
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
        // mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current


    val sliderPositionState = remember {
        mutableStateOf(0f)
    }
    val tipPercentage =(sliderPositionState.value * 100).toInt()
    val splitByState =remember{
        mutableStateOf(1)
    }

    val range = IntRange(start=1, endInclusive  =100)

    val tipAmountState = remember{
        mutableStateOf(0.0)
    }


    Surface(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 2.dp, color = Color(android.graphics.Color.LTGRAY))
    ) {
        Column(modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start) {
            // This input field is used everywhere in app so we made a component for it
            // textFields
            InputField(valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) {
                        return@KeyboardActions
                        onValChange(totalBillState.value.trim())

                    }
                    keyboardController?.hide()

                })
               // if(validState){
                    Row(modifier = Modifier.padding(10.dp),
                    horizontalArrangement =Arrangement.Start ) {
                    Text(text = "Split",
                    modifier = Modifier.align(alignment = Alignment.CenterVertically))
                        Spacer(modifier = Modifier.width(120.dp))
                        Row(modifier = Modifier.padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End) {

                            RoundIconButton(modifier= Modifier.padding(all = 1.dp),
                                imageVector = Icons.Default.Remove ,
                                onClick = {
                                    // Logic
                                    splitByState.value = if(splitByState.value > 1) {
                                        splitByState.value - 1 }
                                    else {
                                        1
                                    }


                                })

                            Text(text = "${splitByState.value}",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 9.dp, end = 9.dp))

                            RoundIconButton(modifier= Modifier
                                .padding(all = 1.dp)
                                .align(alignment = Alignment.CenterVertically),
                                imageVector = Icons.Default.Add,
                                onClick = {
                                    if(splitByState.value<range.last){
                                        splitByState.value =splitByState.value + 1
                                    }
                                })
                        }
                    }

                //Tip Row
                Row (modifier = Modifier.padding( vertical = 12.dp)){
                    Text(text ="Tip",
                    modifier = Modifier
                        .padding(10.dp)
                        .align(alignment = Alignment.CenterVertically))
                    Spacer(modifier = Modifier.width(180.dp))

                    Text(text ="$ ${tipAmountState.value}",modifier = Modifier.align(alignment = Alignment.CenterVertically))
                }

            //Tip Percentage and Slider
            Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "$tipPercentage %")

                // Slider

                // Slider must take intermediate values also

                Slider(value = sliderPositionState.value, onValueChange ={newVal ->
                    sliderPositionState.value = newVal

                    tipAmountState.value=(calculateTotalTip(totalBill = totalBillState.value.toDouble(),tipPercentage=tipPercentage))
//                    Log.d("Slider", "BillForm: $newVal")
                },modifier=Modifier.padding(start = 16.dp,end=16.dp),
                steps = 5,
                onValueChangeFinished = {

                })

                // How to change in Slider that it takes intermidiate values a



            }




//                }else{
//                    Box(){}
//                }
        }
    }
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoneyCounterJetpackComposeTheme() {
        run {
            MyApp {
                Text(text = "Hello Again")
            }
        }
    }
}
