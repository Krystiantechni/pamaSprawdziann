package com.example.sprawdzian

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.sprawdzian.ui.theme.SprawdzianTheme
import androidx.compose.foundation.shape.*
import androidx.compose.ui.draw.clip


// Data class to represent recent activities
data class ActivityItem(val icon: Int, val title: String, val count: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SprawdzianTheme {
                Scaffold { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        onButtonClick = {
                            Toast.makeText(this, "Well done!", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier, onButtonClick: () -> Unit) {
    // Example list of activities
    val activities = listOf(
        ActivityItem(R.drawable.ic_committed_changes, "Committed changes", 22),
        ActivityItem(R.drawable.ic_comment_count, "Comment count", 15),
        ActivityItem(R.drawable.ic_merged_pull_requests, "Merged pull requests", 8),
        ActivityItem(R.drawable.ic_closed_pull_requests, "Closed pull requests", 3)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // User header
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.man_person_icon), // Your provided image
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "Grześ Grzegorzewski", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = "Git statistics",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider()

        // Recent Activities
        Text(
            text = "Recent Activities",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        activities.forEach { activity ->
            ActivityRow(activity)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Display message button
        Button(
            onClick = onButtonClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650A4))
        ) {
            Text(text = "Display message", color = Color.White)
        }
    }
}

@Composable
fun ActivityRow(activity: ActivityItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = activity.icon),
            contentDescription = activity.title,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = activity.title,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp
        )
        Text(
            text = activity.count.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    SprawdzianTheme {
        MainScreen(onButtonClick = {})
    }
}
