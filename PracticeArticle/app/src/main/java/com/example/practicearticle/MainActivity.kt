package com.example.practicearticle

import android.graphics.drawable.PaintDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicearticle.ui.theme.PracticeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeArticleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PracticeArticleWithImage(title = getString(R.string.article_title), firstParagraph = getString(
                                            R.string.first_para), secondParagraph = getString(R.string.second_para))
                }
            }
        }
    }
}

@Composable
fun PracticeArticleWithText(title: String, firstParagraph: String, secondParagraph: String) {
    Column {
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(
                    PaddingValues(16.dp)
                )
        )
        Text(
            text = firstParagraph,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp
            )
        )
        Text(
            text = secondParagraph,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 16.dp
            )
        )

    }
}

@Composable
fun PracticeArticleWithImage(title: String, firstParagraph: String, secondParagraph: String){
    val backgroundImage = painterResource(id = R.drawable.bg_compose_background)
    Column{
        Image(painter = backgroundImage, contentDescription = null, modifier = Modifier.fillMaxWidth())
        PracticeArticleWithText(
            title = title,
            firstParagraph = firstParagraph,
            secondParagraph = secondParagraph
        )
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PracticeArticlePreview() {
    PracticeArticleTheme {
        PracticeArticleWithImage(title = "Jetpack Compose tutorial", firstParagraph = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.", secondParagraph = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.")
    }
}