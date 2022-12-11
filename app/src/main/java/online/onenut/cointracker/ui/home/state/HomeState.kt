package online.onenut.cointracker.ui.home.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class HomeState {
    val showQuickAdd: MutableState<Boolean> = mutableStateOf(false)
}