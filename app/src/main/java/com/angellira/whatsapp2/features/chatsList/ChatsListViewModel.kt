package com.angellira.whatsapp2.features.chatsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.daysUntil
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import kotlin.math.absoluteValue
import kotlin.random.Random


val avatarUrls = mutableListOf(
    "https://randomuser.me/api/portraits/men/32.jpg",
    "https://randomuser.me/api/portraits/women/44.jpg",
    "https://randomuser.me/api/portraits/men/18.jpg",
    "https://randomuser.me/api/portraits/women/65.jpg",
    "https://randomuser.me/api/portraits/men/77.jpg",
    "https://randomuser.me/api/portraits/women/12.jpg",
    "https://randomuser.me/api/portraits/men/89.jpg",
    "https://randomuser.me/api/portraits/women/20.jpg",
    "https://randomuser.me/api/portraits/men/7.jpg",
    "https://randomuser.me/api/portraits/women/30.jpg"
)

@OptIn(FormatStringsInDatetimeFormats::class)
fun LocalDateTime.formattedDateForChatLastMessage(): String {
    val nowLocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val dayUntil = nowLocalDateTime.date.daysUntil(this.date).absoluteValue

    return when {
        dayUntil == 0 -> {
            this.time.format(LocalTime.Format {
                byUnicodePattern("HH:mm")
            })
        }

        dayUntil < 2 -> {
            "ontem"
        }

        dayUntil < 7 -> {
            this.date.format(LocalDate.Format {
                this.dayOfWeek(
                    names = DayOfWeekNames(
                        monday = "segunda",
                        tuesday = "terça",
                        wednesday = "quarta",
                        thursday = "quinta",
                        friday = "sexta",
                        saturday = "sábado",
                        sunday = "domingo"
                    )
                )
            })
        }

        else -> {
            this.format(LocalDateTime.Format {
                byUnicodePattern("dd/MM/yy")
            })
        }
    }
}


sealed class ChatsListState {
    data object Loading : ChatsListState()
    data class Sucess(
        val filters: List<String> = emptyList(),
        val chats: List<Chat> = emptyList(),
        val currentUser: User
    ) : ChatsListState()
}

data class Chat(
    val id: String,
    val avatar: String,
    val name: String,
    val lastMessage: Message,
    val unreadMessage: Int = 0
)

data class Message(
    val id: String, val text: String, val date: String, val isRead: Boolean, val author: User
)

data class User(
    val id: String,
    val name: String,
)

class ChatsListViewModel : ViewModel() {
    private val _state = MutableStateFlow<ChatsListState>(
        ChatsListState.Loading
    )
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val user = fetchUser()
            val filters = fetchFilters()
            val chats = fetchChats()
            delay(Random.nextLong(1000, 3000))
            _state.update {
                ChatsListState.Sucess(
                    filters = filters, chats = chats, currentUser = user
                )
            }
        }

    }

    private fun fetchChats(): List<Chat> {
        avatarUrls.shuffle()
        val avatarIterator = avatarUrls.listIterator()

        return List(10) {
            val localDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                .date.minus(it, DateTimeUnit.DAY)
                .atTime(
                    Random.nextInt(0, 23),
                    Random.nextInt(0, 59),
                    Random.nextInt(0, 59)
                )
            val avatar =
                if (Random.nextBoolean() && avatarIterator.hasNext()) avatarIterator.next() else ""
            Chat(
                id = it.toString(), avatar = avatar, name = "User $it", lastMessage = Message(
                    id = it.toString(),
                    text = "Last message from user $it",
                    date = localDateTime.formattedDateForChatLastMessage(),
                    isRead = Random.nextBoolean(),
                    author = User(id = it.toString(), name = "User $it")
                ), unreadMessage = Random.nextInt(1, 10)
            )
        }
    }


    private fun fetchFilters(): List<String> {
        return listOf("All", "Unread", "Groups")

    }

    private fun fetchUser(): User {
        return User(
            id = "1", name = "Current User"
        )
    }


}


