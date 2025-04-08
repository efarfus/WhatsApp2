package com.angellira.whatsapp2.features.chatsList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.angellira.whatsapp2.ui.theme.WhatsApp2Theme

@Composable
fun ChatsListScreen(modifier: Modifier = Modifier) {
    LazyColumn(modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        //search textfields
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Search, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Search...")
            }
        }

        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val filters = remember { listOf("All", "Unread", "Groups") }
                filters.forEach { filter ->
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Gray)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(text = filter)
                    }
                }
            }
        }

        items(12) {
            val avatarSize = 60.dp
            Row(Modifier.fillMaxWidth()) {
                Box(
                    Modifier
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .size(avatarSize)
                ) {

                }
                Spacer(Modifier.size(16.dp))
                Column(
                    Modifier
                        .heightIn(avatarSize),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Row(
                        Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Name")

                        Text(text = "Last message date")
                    }
                    Row(
                        Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(Modifier.weight(1f)) {
                            Icon(Icons.Default.DoneAll, contentDescription = null)
                            Spacer(Modifier.size(4.dp))
                            Text(text = "last massage")
                        }

                        Box(
                            Modifier
                                .clip(CircleShape)
                                .background(Color.Green)
                                .padding(8.dp, 4.dp)
                        ) {
                            Text(text = "99")
                        }

                    }
                }

            }
        }

        //chats filter
        //chats list
    }
}

@Preview
@Composable
fun ChatsListScreenPreview() {
    WhatsApp2Theme {
        ChatsListScreen()
    }
}