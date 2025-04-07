package com.angellira.whatsapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.angellira.whatsapp2.ui.theme.WhatsApp2Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatsApp2Theme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(
                        title = { Text("WhatsApp 2") },
                        actions = {
                            Icon(Icons.Default.CameraAlt, contentDescription = "Camera")
                            Icon(Icons.Default.MoreVert, contentDescription = "More")
                        },

                    )

                }) { innerPadding ->
                    ChatsListScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ChatsListScreen(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()){
        Text(
            text = "Chats List",
            modifier = Modifier
                .align(Alignment.Center),
            style = TextStyle.Default.copy(
                fontSize = 32.sp
            )
        )
    }
}