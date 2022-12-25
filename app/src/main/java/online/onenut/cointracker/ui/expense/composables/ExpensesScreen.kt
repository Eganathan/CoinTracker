package online.onenut.cointracker.ui.expense.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import online.onenut.cointracker.ui.components.ExpensesListItemComponent
import online.onenut.cointracker.ui.expense.state.ExpensesState
import java.util.*

enum class PrimaryExpenseTag { PRIMARY, CASUAL }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesListScreen(expensesState: ExpensesState) {

    val expenses = expensesState.expenses.collectAsState(initial = listOf())
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Expenses")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                expensesState.createExpense()
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
            items(expenses.value) {
                ExpensesListItemComponent(
                    primaryTitle = it.title ?: "",
                    amount = (it.amount ?: 0).toString()
                )
            }
        }
    }
}