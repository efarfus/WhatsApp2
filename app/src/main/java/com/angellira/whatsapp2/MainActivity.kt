package com.angellira.whatsapp2

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.runtime.LaunchedEffect
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

sealed class NavItem(
    val icon: ImageVector,
    val label: String,
) {
    data object Chats : NavItem(
        icon = Icons.AutoMirrored.Filled.Message,
        label = "Chats"
    )

    data object Status : NavItem(
        icon = Icons.Default.DataSaverOff,
        label = "Status"
    )

    data object Communities : NavItem(
        icon = Icons.Default.People,
        label = "Communities"
    )

    data object Calls : NavItem(
        icon = Icons.Default.Call,
        label = "Calls"
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
private fun App() {
    val items = remember {
        listOf(
            NavItem.Chats,
            NavItem.Status,
            NavItem.Communities,
            NavItem.Calls
        )

    }
    var selectedItem by remember { mutableStateOf(items.first()) }
    val pagerState = rememberPagerState { items.size }
    LaunchedEffect(selectedItem) {
        pagerState.animateScrollToPage(
            items.indexOf(selectedItem)
        )
    }
    LaunchedEffect(pagerState.targetPage) {
        selectedItem = items[pagerState.targetPage]
    }
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
        NavigationBar {
            items.forEach { navItem ->
                NavigationBarItem(
                    selected = navItem == selectedItem,
                    onClick = {
                        selectedItem = navItem
                    },

                    icon = {
                        Icon(
                            imageVector = navItem.icon,
                            contentDescription = navItem.label,
                        )
                    },
                    label = { Text(navItem.label) }
                )
            }

        }
}) {
    innerPadding ->
    HorizontalPager(pagerState, Modifier.padding(innerPadding)) { page ->
        when (items[page]) {
            is NavItem.Chats -> ChatsListScreen()
            is NavItem.Status -> UpdatesScreen()
            is NavItem.Communities -> CommunitiesScreen()
            is NavItem.Calls -> CallsScreen()
        }
    }
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

@Composable
fun UpdatesScreen(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        Text(
            text = "Updates",
            modifier = Modifier.align(Alignment.Center),
            style = TextStyle.Default.copy(
                fontSize = 32.sp
            )
        )
    }
}

@Composable
fun CommunitiesScreen(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        Text(
            text = "Communities",
            modifier = Modifier.align(Alignment.Center),
            style = TextStyle.Default.copy(
                fontSize = 32.sp
            )
        )
    }
}

@Composable
fun CallsScreen(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        Text(
            text = "Calls",
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