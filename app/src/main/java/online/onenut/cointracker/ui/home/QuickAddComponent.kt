package online.onenut.cointracker.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuickAddComponent(
    onCancel: () -> Unit,
    onDone: () -> Unit
) {
    Dialog({
        onCancel.invoke()
    }) {
        Card(
            Modifier
                .heightIn(min = 130.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 35.dp, topEnd = 35.dp, bottomStart = 35.dp, bottomEnd = 35.dp
            ),
            border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.5f)),
        ) {
            Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                TextField(
                    value = TextFieldValue("Quick Title"),
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    shape = RoundedCornerShape(
                        bottomStart = 0.dp, bottomEnd = 0.dp, topStart = 15.dp, topEnd = 15.dp
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
                        .fillMaxWidth()
                        .background(Color.Black)

                )
                TextField(
                    value = TextFieldValue("Amount: 0.0"),
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth(),
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
                        .padding(horizontal = 25.dp, vertical = 15.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.Green),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilterChip(
                        selected = true,
                        onClick = { /*TODO*/ },
                        border = BorderStroke(1.dp, Color.Black),
                        colors = ChipDefaults.filterChipColors(
                            backgroundColor = Color.Red.copy(
                                0.4f
                            )
                        ),
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(5.dp)
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
                            .padding(5.dp)
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
    }
}