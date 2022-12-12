package online.onenut.cointracker.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import online.onenut.cointracker.ui.home.state.Type

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun QuickAddComponent(
    title: MutableState<TextFieldValue>,
    amount: MutableState<TextFieldValue>,
    type: MutableState<Type>,
    onCancel: () -> Unit,
    onDone: () -> Unit
) {
    val focusRequester = FocusRequester()
    val fm = LocalFocusManager.current

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
                    label = { Text(text = "Title") },
                    value = title.value,
                    onValueChange = {
                        title.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .focusRequester(focusRequester = focusRequester),
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
                    keyboardActions = KeyboardActions(onNext = { fm.moveFocus(FocusDirection.Next) }),
                    singleLine = true,
                    maxLines = 1
                )
                Divider(
                    Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.Black)

                )
                TextField(
                    label = { Text(text = "Amount") },
                    value = amount.value,
                    onValueChange = {
                        if (it.text.toDoubleOrNull() != null)
                            amount.value = it
                    },
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
                    maxLines = 1
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp, vertical = 15.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.LightGray.copy(0.3f)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilterChip(
                        selected = type.value == Type.EXPENSE,
                        onClick = {
                            type.value = Type.EXPENSE
                        },
                        border = if (type.value == Type.EXPENSE) BorderStroke(
                            1.dp,
                            Color.Black
                        ) else null,
                        //  colors = ChipDefaults.filterChipColors(backgroundColor = Color.White),
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
                        selected = type.value == Type.INCOME,
                        // colors = ChipDefaults.filterChipColors(backgroundColor = Color.White),
                        border = if (type.value == Type.INCOME) BorderStroke(
                            1.dp,
                            Color.Black
                        ) else null,
                        onClick = {
                            type.value = Type.INCOME
                        },
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

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    TextButton(onClick = { onDone.invoke() }) {
                        Text(text = "ADD TRANSACTION")
                    }
                }
            }
        }


        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}