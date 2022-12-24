package online.onenut.cointracker.ui.expense.composables

import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import online.onenut.cointracker.PreviewTagChip
import online.onenut.cointracker.data.model.Expense
import online.onenut.cointracker.ui.components.ExpensesListItemComponent
import online.onenut.cointracker.ui.expense.state.ExpensesState
import online.onenut.cointracker.widgets.ScrollableChips
import java.time.LocalDateTime
import java.util.*

enum class PrimaryExpenseTag { PRIMARY, CASUAL }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesListScreen(expensesState: ExpensesState) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Expenses")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                expensesState.createExpense(
                    Expense(
                        title = "S ${LocalDateTime.now().nano}",
                        amount = Random().nextDouble()
                    )
                )
                expensesState.getExpenses()
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
                Text("Size:".plus(expensesState.expenses.value.size))
            }

            items(expensesState.expenses.value) {
                ExpensesListItemComponent(
                    primaryTitle = it.title ?: "",
                    amount = (it.amount ?: 0).toString()
                )
            }
        }
    }
}