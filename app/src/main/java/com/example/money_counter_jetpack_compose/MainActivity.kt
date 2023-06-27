package com.example.money_counter_jetpack_compose





import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
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

@Preview
@Composable
fun TopHeader(totalPerPerson : Double = 134.0){
    // Surface Bnaya
    Surface(modifier = Modifier
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
    val totalBillState = remember{
        mutableStateOf("")
    }
    // State variable to check Valid Input in text field\
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
        // mutableStateOf(false)
    }
    val keyboardController=LocalSoftwareKeyboardController.current

    Surface(modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(), shape = RoundedCornerShape(corner= CornerSize(8.dp)),
        border = BorderStroke(width = 2.dp, color = Color(android.graphics.Color.LTGRAY))
    ) {
        Column {

            // textFields
            InputField(valueState = totalBillState ,
                labelId ="Enter Bill" ,
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions{
                    if(!validState){
                        return@KeyboardActions
                        //Todo - onvaluechanges

                        keyboardController?.hide()
                    }
                }
            )

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