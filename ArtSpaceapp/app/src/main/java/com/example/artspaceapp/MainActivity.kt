@file:Suppress("UNUSED_EXPRESSION")

package com.example.artspaceapp

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

val pictures =
    hashMapOf(0 to R.drawable.art1, 1 to R.drawable.art2, 2 to R.drawable.art3)
val captions =
   hashMapOf(0 to R.string.caption1, 1 to R.string.caption2, 2 to R.string.caption3)
val dates =
    hashMapOf(0 to R.string.date1, 1 to R.string.date2, 2 to R.string.date3)
val artists =
    hashMapOf(0 to R.string.artist1, 1 to R.string.artist2, 2 to R.string.artist3)

val sizeOfCollection = pictures.size

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArtSpaceApp() {
    val squareSize = 48.dp
    var pictureNumber by remember {
        mutableStateOf(0)
    }
    val swipeableState = rememberSwipeableState(pictureNumber)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states


    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight()
            .wrapContentHeight()
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
        ,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ArtSpace(pictureNumber)
        ContentDesc(pictureNumber)


        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(onClick = {
                if (--pictureNumber > -1) null else pictureNumber = sizeOfCollection - 1
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Previous")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { if (++pictureNumber < sizeOfCollection) null else pictureNumber = 0 },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Next")
            }
        }
    }

}

@Composable
fun ArtSpace(pictureNumber: Int) {
    Surface(
        modifier = Modifier.padding(32.dp),
        border = BorderStroke(width = 2.dp, color = Color.Black),
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Image(
            alignment = Alignment.Center,
            painter = painterResource(id = pictures[pictureNumber]!!),
            contentDescription = stringResource(captions[pictureNumber]!!),
            modifier = Modifier.padding(12.dp)
        )
    }
}


@Composable
fun ContentDesc(pictureNumber: Int) {
    Surface(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .wrapContentWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = stringResource(captions[pictureNumber]!!), fontSize = 30.sp)
            Row {
                Text(text = stringResource(artists[pictureNumber]!!), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = " ("+stringResource(dates[pictureNumber]!!)+")", fontSize = 20.sp)
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    ArtSpaceAppTheme {
        ArtSpaceApp()
    }
}
enum class Daypart {
    MORNING,
    AFTERNOON,
    EVENING
}
data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int,
)

fun main() {

    val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0)
    val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
    val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
    val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
    val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
    val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)
    val events = mutableListOf<Event>(event1, event2, event3, event4, event5, event6)
    val shortEvents = events.filter{it.durationInMinutes < 60}
    val daypartEvents = events.groupBy { it.daypart }
}

