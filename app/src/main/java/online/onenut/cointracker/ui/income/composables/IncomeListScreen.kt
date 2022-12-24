package online.onenut.cointracker.ui.income.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import online.onenut.cointracker.data.model.Income
import online.onenut.cointracker.ui.components.IncomeListItemComponent
import online.onenut.cointracker.ui.income.states.IncomeListState
import java.time.LocalDateTime
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeListScreen(state: IncomeListState) {
    val incomes = state.incomes.collectAsState(initial = null)
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Incomes")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.createIncomes(
                    Income(
                        title = "S ${LocalDateTime.now().nano}",
                        amount = Random().nextDouble()
                    )
                )
            }) {
                Text("+")
            }
        }
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(it),
            contentPadding = PaddingValues(horizontal = 15.dp, vertical = 5.dp)
        )
        {
            item {
            }

            incomes.value?.let {
                items(it) {
                    IncomeListItemComponent(
                        primaryTitle = it.title ?: "",
                        amount = (it.amount ?: 0).toString()
                    )
                }
            }
        }
    }
}