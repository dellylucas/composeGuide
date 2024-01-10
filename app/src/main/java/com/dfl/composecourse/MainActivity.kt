package com.dfl.composecourse

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dfl.composecourse.ui.theme.ComposeCourseTheme

class MainActivity : ComponentActivity() {
    val list = listOf(
        Message("primero", "once"),
        Message("dos", "doce"),
        Message("tres", "trece"),
        Message("cuatro", "catorce"),
        Message("dos", "doce"),
        Message("tres", "trece"),
        Message("cuatro", "catorce"),
        Message("dos", "doce"),
        Message("tres", "trece"),
        Message("cuatro", "catorce"),
        Message("dos", "doce"),
        Message("tres", "trece"),
        Message("cuatro", "catorce"),
        Message("dos", "doce"),
        Message("tres", "trece"),
        Message("cuatro", "catorce"),
        Message("dos", "doce"),
        Message("tres", "trece"),
        Message("cuatro", "catorce")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCourseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Column {
                        Greeting("Juan")
                        Conversation(list)
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painterResource(R.drawable.image_test),
                contentScale = ContentScale.Crop,
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(50.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.background, CircleShape)

            )


            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))

            // We keep track if the message is expanded or not in this
            // variable
            var isExpanded by rememberSaveable { mutableStateOf(false) }

            // surfaceColor will be updated gradually from one color to the other
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                label = ""
            )

            Column(modifier = Modifier.clickable { isExpanded = !isExpanded })  {
                Text(
                    text = "Hola $name!!!",
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 11.dp,
                    // surfaceColor color will be changing gradually from primary to surface
                    color = surfaceColor,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier.animateContentSize().padding(1.dp)
                ) {
                    Text(
                        text = "no compraste no comprasteno comprasteno comprasteno comprasteno comprasteno comprasteno comprasteno comprasteno comprasteno comprasteno compraste$name naa",
                        modifier = Modifier.padding(all = 4.dp),
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

    }

    @Composable
    fun Conversation(messages: List<Message>) {

        LazyColumn {
            items(messages) { message ->
                Greeting(message.author)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        ComposeCourseTheme {
            Conversation(list)
        }
    }


    @Preview(
        showBackground = true,
        name = "Light Mode"
    )
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        name = "Dark Mode"
    )
    @Composable
    fun GreetingPreview() {
        ComposeCourseTheme {
            Surface {
                Greeting("Delly")
            }
        }
    }

    data class Message(val author: String, val body: String)
}

