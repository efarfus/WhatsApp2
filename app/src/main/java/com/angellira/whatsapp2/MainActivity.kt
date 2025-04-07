package com.angellira.whatsapp2

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.DataSaverOff
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.angellira.whatsapp2.ui.theme.WhatsApp2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatsApp2Theme {
                App()
            }
        }
    }
}

data class NavItem(
    val icon: ImageVector,
    val label: String,
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun App() {
    val items = remember {
        listOf(
            NavItem(
                icon = Icons.AutoMirrored.Filled.Message, label = "Chats"
            ), NavItem(
                icon = Icons.Default.DataSaverOff, label = "Status"
            ),NavItem(
                icon = Icons.Default.People, label = "Communities"
            ),
            NavItem(
                icon = Icons.Default.Call, label = "Calls"
            ),

        )

    }
    var selectedItem by remember { mutableStateOf(items.first()) }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text("WhatsApp 2") },
            actions = {
                Row(
                    Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Camera")
                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                }
            },

            )

    }, bottomBar = {
        BottomAppBar {
            items.forEach{ navItem ->
                NavigationBarItem(
                    selected = navItem == selectedItem,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            imageVector = navItem.icon,
                            contentDescription = navItem.label,
                        )
                }, label = { Text(navItem.label) })
            }

        }
    }) { innerPadding ->
        ChatsListScreen(Modifier.padding(innerPadding))
    }
}


@Composable
fun ChatsListScreen(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        Text(
            text = "Chats List",
            modifier = Modifier.align(Alignment.Center),
            style = TextStyle.Default.copy(
                fontSize = 32.sp
            )
        )
    }
}

@Preview
@Composable
fun AppPreview() {
    WhatsApp2Theme {
        App()
    }
}