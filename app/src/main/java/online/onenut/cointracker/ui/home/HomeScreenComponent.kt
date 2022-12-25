package online.onenut.cointracker.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import online.onenut.cointracker.strings.NavRoutes
import online.onenut.cointracker.ui.home.state.HomeState

@Composable
fun HomeScreenComponent(
    homeState: HomeState,
    paddingValues: PaddingValues,
    onQuickADD: () -> Unit,
    onExpenseClick: () -> Unit = {},
    onIncomeClick: () -> Unit = {},
    onRecentEntityClick: () -> Unit = {},
) {
    if (homeState.showQuickAdd.value)
        QuickAddComponent(
            onDone = {
                homeState.quickAdd()
                homeState.showQuickAdd.value = false
            },
            onCancel = { homeState.showQuickAdd.value = false },
            title = homeState.title,
            amount = homeState.amount,
            type = homeState.type
        )


    Column(
        Modifier
            .padding(paddingValues)
            .padding(top = 35.dp)
            .fillMaxSize()
            .padding(horizontal = 25.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Card(
            Modifier
                .heightIn(min = 190.dp)
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            shape = RoundedCornerShape(
                topStart = 25.dp,
                topEnd = 25.dp,
                bottomEnd = 5.dp,
                bottomStart = 5.dp
            ),
            elevation = 2.dp,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            //Summary Top
        }

        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(150.dp)
                .padding(bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                Modifier
                    .heightIn(min = 150.dp)
                    .weight(0.4f)
                    .clip(
                        RoundedCornerShape(
                            topStart = 5.dp,
                            topEnd = 5.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 5.dp
                        )
                    )
                    .clickable { homeState.navController.navigate(NavRoutes.expensesListScreen) },
                elevation = 1.dp,
                shape = RoundedCornerShape(
                    topStart = 5.dp,
                    topEnd = 5.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 5.dp
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {

                Column(
                    Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Surface() {

                    }
                    Text(
                        text = "  Expenses     >>>",
                        Modifier
                            .fillMaxWidth()
                            .background(Color.Black),
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.width(6.dp))
            Card(
                Modifier
                    .heightIn(min = 150.dp)
                    .weight(0.4f)
                    .clip(
                        RoundedCornerShape(
                            topStart = 5.dp,
                            topEnd = 5.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 5.dp
                        )
                    )
                    .clickable { homeState.navController.navigate(NavRoutes.incomesListScreen) },
                elevation = 1.dp,
                shape = RoundedCornerShape(
                    topStart = 5.dp,
                    topEnd = 5.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 5.dp
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Column(
                    Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Surface() {

                    }
                    Text(
                        text = "  Income     >>>",
                        Modifier
                            .fillMaxWidth()
                            .background(Color.Black),
                        color = Color.White
                    )
                }
            }

        }

        Divider(
            Modifier
                .padding(top = 10.dp, start = 0.dp, end = 0.dp)
                .height(5.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(
                    brush = Brush.radialGradient(
                        0.0f to Color.Red.copy(alpha = 0.3f),
                        0.5f to Color.Green.copy(alpha = 0.9f),
                        1.0f to Color.Red.copy(alpha = 0.3f),
                        center = Offset(0f, 15000f),
                        radius = 100f,
                        tileMode = TileMode.Repeated
                    )
                ), thickness = 2.dp
        )

        LazyColumn(modifier = Modifier.padding(top = 10.dp)) {

            item {
                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = "Recently Added...  Size: ${homeState.recentlyAdded.value?.size}")
                }
            }
            items(homeState.recentlyAdded.value.orEmpty()) {
                Card(
                    Modifier
                        .heightIn(min = 85.dp)
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = it.title ?: "")
                            Text(text = it.amount.toString())
                        }
                    }
                }
            }
        }

    }
}