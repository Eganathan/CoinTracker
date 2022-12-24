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
                ExpensesListCard(
                    primaryTitle = it.title ?: "",
                    amount = (it.amount ?: 0).toString()
                )
            }
        }
    }
}

@Composable
fun ExpensesListCard(
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    primaryTitle: String = "Office Rent Payment",
    secondaryTitle: String = "Chennai office last months payment- sdjnflskjdfnlsjkdnfuwernfljksdcnskjdhflweidfnlwfnwueirnfukrthkqwnbfiwueyrgwbfwiylerbfougrflajhsdbfkayrgtwquyerbwuyfuwyfkquywegr",
    amount: String = "9999999999999999",
    primaryTag: PrimaryExpenseTag = PrimaryExpenseTag.PRIMARY,
    tags: List<String> = listOf("Rent", "Office", "GPay", "HDFC"),
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.5.dp)
            .defaultMinSize(minHeight = 65.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(7.dp))
            .wrapContentHeight()
            .clickable(
                enabled = enable,
                onClickLabel = ""
            ) { onClick.invoke() },
        backgroundColor = Color.White,
        shape = RoundedCornerShape(7.dp),
    ) {
        Row(
            Modifier
                .padding(vertical = 5.dp, horizontal = 10.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(weight = 0.8f)
                    .padding(top = 5.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(text = primaryTitle, style = MaterialTheme.typography.titleMedium)
//                Text(
//                    text = secondaryTitle,
//                    style = MaterialTheme.typography.titleSmall.copy(color = Color.Black.copy(alpha = 0.4f)),
//                    modifier = Modifier.padding(start = 4.dp, top = 3.dp),
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 1,
//                    softWrap = true
//                )

                ScrollableChips(stickyHeaderText = "29th,Dec 2022", tags = tags)
            }
            Column(
                modifier = Modifier
                    .weight(0.3f)
                    .padding(top = 10.dp, start = 5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = AnnotatedString(text = "Rs.").plus(AnnotatedString(amount)),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier
                        .padding(bottom = 0.dp)
                        .offset(y = 5.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = true
                )
                PreviewTagChip(
                    text = primaryTag.name.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                    backgroundColor = if (primaryTag == PrimaryExpenseTag.PRIMARY) Color.Green.copy(
                        alpha = 0.1f
                    ) else Color.Red.copy(0.2f)
                )
            }
        }

    }
}