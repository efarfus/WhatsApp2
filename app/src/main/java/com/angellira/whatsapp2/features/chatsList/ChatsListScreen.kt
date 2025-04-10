package com.angellira.whatsapp2.features.chatsList

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import kotlin.random.Random


@Composable
fun ChatsListScreen(modifier: Modifier = Modifier, state: ChatsListState) {
    when (state) {
        ChatsListState.Loading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }

        is ChatsListState.Sucess -> {
            LazyColumn(
                modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
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
                        val filters = state.filters
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

                items(state.chats) { chat ->
                    val avatarSize = 60.dp
                    Row(Modifier.fillMaxWidth()) {
                        if (chat.avatar.isBlank()) {
                            Box(
                                Modifier
                                    .clip(CircleShape)
                                    .background(
                                        Color(
                                            Random.nextInt(0, 255),
                                            Random.nextInt(0, 255),
                                            Random.nextInt(0, 255)
                                        )
                                    )
                                    .size(avatarSize),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = chat.name.first().uppercase(),
                                    color = Color.White,
                                    fontSize = 24.sp
                                )
                            }
                        } else {
                            SubcomposeAsyncImage(
                                model = chat.avatar,
                                contentDescription = null,
                                loading = {
                                    Box(modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape)
                                        .shimmer()
                                    )
                                },
                                modifier = Modifier
                                    .size(avatarSize)
                                    .clip(CircleShape)
                            )
                        }



                        Spacer(Modifier.size(16.dp))
                        Column(
                            Modifier.heightIn(avatarSize),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    chat.name, Modifier.weight(1f), style = TextStyle.Default.copy(
                                        fontSize = 18.sp, fontWeight = FontWeight.Bold
                                    ), overflow = TextOverflow.Ellipsis, maxLines = 1
                                )
                                Spacer(Modifier.size(8.dp))
                                Text(chat.lastMessage.date)
                            }
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(Modifier.weight(1f)) {
                                    if (state.currentUser != chat.lastMessage.author) {
                                        Icon(Icons.Default.DoneAll, contentDescription = null)
                                        Spacer(Modifier.size(4.dp))
                                    }
                                    Text(
                                        text = chat.lastMessage.text,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1
                                    )
                                }
                                Spacer(Modifier.size(8.dp))

                                Box(
                                    Modifier
                                        .clip(CircleShape)
                                        .size(30.dp)
                                        .background(Color.Green)
                                        .padding(4.dp)
                                ) {
                                    Text("${chat.unreadMessage}", Modifier.align(Alignment.Center))
                                }

                            }
                        }

                    }
                }
            }

        }
    }

}

@Composable
private fun Modifier.shimmer(
    colors: List<Color> =
        listOf(
            Color.Gray.copy(alpha = 0.5f),
            Color.Gray.copy(alpha = 0.1f),
            Color.Gray.copy(alpha = 0.5f),
        )
): Modifier {
    val infiniteTransition =
        rememberInfiniteTransition(label = "infiniteTransation")
    val localConfig = LocalConfiguration.current
    val target = (localConfig.screenWidthDp * 3).toFloat()
    val scale = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = target,
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ),
        label = "shimmer"
    )
    return this.then(
        Modifier.background(
            Brush.linearGradient(
                colors = colors,
                end = Offset(x = scale.value, y = scale.value),
            )
        )
    )
}