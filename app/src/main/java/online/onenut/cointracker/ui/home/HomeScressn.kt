package online.onenut.cointracker.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    Column(
        Modifier
            .padding(top = 35.dp)
            .fillMaxSize()
            .padding(horizontal = 25.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            Modifier
                .heightIn(min = 190.dp)
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            shape = RoundedCornerShape(35.dp),
            elevation = 2.dp,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            //Summary Top
        }

        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(150.dp)
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                Modifier
                    .heightIn(min = 150.dp)
                    .weight(0.4f),
                elevation = 1.dp,
                shape = RoundedCornerShape(
                    topStart = 35.dp, topEnd = 35.dp, bottomStart = 0.dp, bottomEnd = 35.dp
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                //Summary Top
            }
            Spacer(modifier = Modifier.width(15.dp))
            Card(
                Modifier
                    .heightIn(min = 150.dp)
                    .weight(0.4f),
                elevation = 1.dp,
                shape = RoundedCornerShape(
                    topStart = 35.dp, topEnd = 35.dp, bottomStart = 35.dp, bottomEnd = 0.dp
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                //Summary Top
            }

        }

        Card(
            Modifier
                .heightIn(min = 130.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 0.dp, topEnd = 0.dp, bottomStart = 35.dp, bottomEnd = 35.dp
            ),
            border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.5f)),
        ) {
            Column(verticalArrangement = Arrangement.SpaceEvenly) {

                TextField(
                    value = TextFieldValue("Quick Title"),
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                        .padding(top = 5.dp),
                    shape = RoundedCornerShape(
                        bottomStart = 0.dp, bottomEnd = 0.dp, topStart = 0.dp, topEnd = 0.dp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    //  leadingIcon = { Icon(imageVector = Icons.Default., contentDescription = "")}
                    //    label = { Text(text = "Amount: 0.0")}
                )
                Divider(
                    Modifier
                        .height(1.dp)
                        .padding(horizontal = 5.dp)
                        .fillMaxWidth()
                        .background(Color.Black)

                )
                TextField(
                    value = TextFieldValue("Amount: 0.0"),
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 0.dp),
                    shape = RoundedCornerShape(
                        bottomStart = 15.dp, bottomEnd = 15.dp, topStart = 0.dp, topEnd = 0.dp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    //  leadingIcon = { Icon(imageVector = Icons.Default., contentDescription = "")}
                    //    label = { Text(text = "Amount: 0.0")}
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp, vertical = 10.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.Green),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilterChip(
                        selected = true,
                        onClick = { /*TODO*/ },
                        border = BorderStroke(1.dp, Color.Black),
                        colors = ChipDefaults.filterChipColors(backgroundColor = Color.Red.copy(0.4f)),
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(start = 5.dp)
                    ) {
                        Text(
                            text = "Expense",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    FilterChip(
                        selected = true,
                        colors = ChipDefaults.filterChipColors(
                            backgroundColor = Color.Green.copy(
                                0.4f
                            )
                        ),
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(end = 5.dp)
                    ) {
                        Text(
                            text = "Income",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        Divider(
            Modifier
                .padding(top = 10.dp, start = 5.dp, end = 5.dp)
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

        LazyVerticalGrid(columns = GridCells.Fixed(4), modifier = Modifier.padding(top = 10.dp)) {
            items(4) {
                Card(
                    Modifier
                        .size(85.dp)
                        .padding(5.dp), shape = RoundedCornerShape(10.dp)
                ) {}
            }
        }
    }
}