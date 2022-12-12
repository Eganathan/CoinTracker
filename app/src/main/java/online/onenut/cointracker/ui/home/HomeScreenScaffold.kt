package online.onenut.cointracker.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import online.onenut.cointracker.ui.home.state.HomeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenScaffold(homeState: HomeState) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Coin Tracker!")
            }, actions = {}, navigationIcon = {

            })
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    homeState.showQuickAdd.value = true
                },
                //  colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
            ) {
                Row(
                    Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Add, null, tint = Color.White)
                    Text(text = "Quick ADD", color = Color.White, textAlign = TextAlign.Center)
                }
            }
        },
        backgroundColor = Color.Red.copy(0.1f)
    ) {
        HomeScreenComponent(
            homeState = homeState,
            paddingValues = it,
            onQuickADD = {
                homeState.showQuickAdd.value = true;
                homeState.quickAdd()
            })
    }

}