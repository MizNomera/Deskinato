package com.example.deskinato

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.deskinato.ui.theme.DeskinatoTheme
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeskinatoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {


                        var estimate by remember { mutableStateOf("") }


                        TextField(
                            textStyle = TextStyle(
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center
                            ), value = estimate.toString(), onValueChange = {

                                estimate = it
                            })


                        Button({
                            val client = HttpClient(CIO)
                            runBlocking {
                                val response: HttpResponse =
                                    client.get("http://10.2.2.2:8080/move?height=" + estimate)
                            }

                        }
                        ) {
                            Text(text = "Submit", fontSize = 35.sp)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

fun subtract(Length: Float?): Float {
    return (Length ?: 1f) - 1f
}

