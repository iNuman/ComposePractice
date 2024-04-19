package com.example.composepractice

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.ui.theme.ComposePracticeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            Column(Modifier.fillMaxSize()) {
                val color = remember {
                    mutableStateOf(Color.Yellow)
                }
//                val color2 = remember {
//                    mutableStateOf(Color.Magenta)
//                }
                ColorBox(
                    Modifier
                        .height(100.dp)
                        .fillMaxSize()
                ) {
                    color.value = it
                }
                Box(
                    modifier = Modifier
                        .background(color.value)
                        .height(120.dp)
                        .fillMaxSize()
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(listOf("this", "is", "oxms", "sd")) { index, item ->
                        val color2 = remember {
                            mutableStateOf(Color.Magenta)
                        }
                        Text(
                            text = "$item $index",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color2.value)
                                .padding(vertical = 24.dp)
                                .clickable {
                                    color2.value = Color(
                                        Random.nextFloat(),
                                        Random.nextFloat(),
                                        Random.nextFloat(),
                                        1f
                                    )
                                    Toast
                                        .makeText(context, item, Toast.LENGTH_SHORT)
                                        .show()
                                }

                        )
                    }
            }


//                items(5000) {
//                    Text(
//                        text = "Item $it",
//                        fontSize = 25.sp,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 24.dp)
//                    )
//                }
            }

        }
    }
}


@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit
) {

    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )
        })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePracticeTheme {
        Column(Modifier.fillMaxSize()) {
            val color = remember {
                mutableStateOf(Color.Yellow)
            }
            ColorBox(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                color.value = it
            }
            Box(
                modifier = Modifier
                    .background(color.value)
                    .weight(1f)
                    .fillMaxSize()
            )
        }
    }
}