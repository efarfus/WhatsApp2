package com.angellira.whatsapp2

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
import androidx.compose.material.icons.filled.Search
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
import com.angellira.whatsapp2.features.chatsList.ChatsListScreen
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

class BottomAppBarItem(
    val icon: ImageVector,
    val label: String,
)

class TopAppBarItem(
    val title: String,
    val icon: List<ImageVector> = emptyList(),
)

sealed class ScreenItem(
    val topAppItem: TopAppBarItem, val bottomAppItem: BottomAppBarItem
) {
    data object Chats : ScreenItem(
        bottomAppItem = BottomAppBarItem(
            icon = Icons.AutoMirrored.Filled.Message, label = "Chats"
        ), topAppItem = TopAppBarItem(
            title = "WhatsApp 2", icon = listOf(
                Icons.Default.CameraAlt, Icons.Default.MoreVert
            )
        )
    )

    data object Status : ScreenItem(
        bottomAppItem = BottomAppBarItem(
            icon = Icons.Default.DataSaverOff, label = "Status"
        ), topAppItem = TopAppBarItem(
            title = "Status", icon = listOf(
                Icons.Default.Search, Icons.Default.MoreVert
            )
        )

    )

    data object Communities : ScreenItem(
        bottomAppItem = BottomAppBarItem(
            icon = Icons.Default.People, label = "Communities"
        ), topAppItem = TopAppBarItem(
            title = "Communities", icon = listOf(
                Icons.Default.MoreVert
            )
        )
    )

    data object Calls : ScreenItem(
        bottomAppItem = BottomAppBarItem(
            icon = Icons.Default.Call, label = "Calls"
        ), topAppItem = TopAppBarItem(
            title = "Calls", icon = listOf(
                Icons.Default.Search, Icons.Default.MoreVert
            )
        )
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
private fun App() {
    val screens = remember {
        listOf(
            ScreenItem.Chats, ScreenItem.Status, ScreenItem.Communities, ScreenItem.Calls
        )

    }
    var currentScreen by remember { mutableStateOf(screens.first()) }
    val pagerState = rememberPagerState { screens.size }
    LaunchedEffect(currentScreen) {
        pagerState.animateScrollToPage(
            screens.indexOf(currentScreen)
        )
    }
    LaunchedEffect(pagerState.targetPage) {
        currentScreen = screens[pagerState.targetPage]
    }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text(currentScreen.topAppItem.title) },
            actions = {
                Row(
                    Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    currentScreen.topAppItem.icon.forEach { icon ->
                        Icon(
                            icon, contentDescription = null,
                        )
                    }
                }
            },

            )

    }, bottomBar = {
        NavigationBar {
            screens.forEach { screen ->
                with(screen.bottomAppItem) {
                    NavigationBarItem(selected = screen == currentScreen, onClick = {
                        currentScreen = screen
                    }, icon = {
                        Icon(
                            icon, label
                        )
                    }, label = { label })
                }
            }

        }
    }) { innerPadding ->
        HorizontalPager(pagerState, Modifier.padding(innerPadding)) { page ->
            when (screens[page]) {
                is ScreenItem.Chats -> ChatsListScreen()
                is ScreenItem.Status -> UpdatesScreen()
                is ScreenItem.Communities -> CommunitiesScreen()
                is ScreenItem.Calls -> CallsScreen()
            }
        }
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