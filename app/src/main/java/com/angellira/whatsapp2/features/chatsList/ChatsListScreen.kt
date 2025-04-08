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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        //search textfields
        item {
            Row(
                Modifier
                    .clip(CircleShape)
                    .padding(16.dp)
                    .background(Color.Gray)
                    .fillMaxWidth()
            ) {
                Icon(Icons.Default.Search, contentDescription = null)
                Spacer(
                    Modifier.size(8.dp)
                )
                Text(text = "Search...")
            }
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                val filters = remember {
                    mutableListOf("All", "Unread", "Groups")
                }


                filters.forEach { filter ->
                    Box(
                        modifier = Modifier
                            .padding(16.dp, 8.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    ) {
                        Text(text = filter)
                    }
                }
            }
        }
        items(12) {
            Row(Modifier.fillMaxWidth()) {
                Box(
                    Modifier
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .size(54.dp)
                ) {

                }
                Spacer(Modifier.size(8.dp))
                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(8.dp)
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