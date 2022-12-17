package online.onenut.cointracker.ui.expense.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import java.util.*

enum class PrimaryExpenseTag { PRIMARY, CASUAL }


@Composable
fun ExpensesListScreen() {
    LazyColumn(
        Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 5.dp)
    )
    {
        items(100) {
            ExpensesListCard()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
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
                LazyRow(
                    Modifier
                        .padding(start = 1.dp, top = 7.dp)
                        .heightIn(min = 22.dp, max = 26.dp),
                )
                {
                    stickyHeader {
                        PreviewTagChip(
                            text = "29th,Dec 2022",
                            backgroundColor = Color.LightGray,
                            contentColor = Color.White
                        )
                    }
                    items(tags) { PreviewTagChip(text = it) }
                }
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
                PreviewTagChip(text = primaryTag.name.lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                    backgroundColor = if (primaryTag == PrimaryExpenseTag.PRIMARY) Color.Green.copy(
                        alpha = 0.1f
                    ) else Color.Red.copy(0.2f)
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PreviewTagChip(
    text: String,
    enabled: Boolean = false,
    onClick: () -> Unit = {},
    backgroundColor: Color = Color.Red.copy(alpha = 0.1f),
    contentColor: Color = Color.Black.copy(alpha = 0.7f)
) {
    Chip(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .padding(0.dp)
            .padding(end = 5.dp)
            .defaultMinSize(minHeight = 20.dp),
        colors = ChipDefaults.chipColors(disabledBackgroundColor = backgroundColor)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(color = contentColor),
            modifier = Modifier.padding(0.dp)
        )
    }
}