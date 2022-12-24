package online.onenut.cointracker.ui.income.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import online.onenut.cointracker.ui.components.IncomeListItemComponent
import online.onenut.cointracker.ui.income.states.IncomeListState
import java.util.*

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)
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
                state.scope.launch {
                    state.modalBottomSheetState.show()
                    state.keyboardController?.show()
                }
            }) {
                Text("+")
            }
        },
    ) {
        ModalBottomSheetLayout(
            sheetState = state.modalBottomSheetState,
            sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
            sheetContent = {
                IncomeSheetContent(title = state.titleTF,
                    amount = state.amountTF,
                    description = state.descriptionTF,
                    enabled = true,
                    onCreate = {
                        state.createIncomes()
                    })
            }) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(it),
                contentPadding = PaddingValues(horizontal = 15.dp, vertical = 5.dp)
            ) {
                incomes.value?.let {
                    items(it) {
                        IncomeListItemComponent(
                            primaryTitle = it.title ?: "", amount = (it.amount ?: 0).toString()
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeSheetContent(
    title: MutableState<TextFieldValue>,
    description: MutableState<TextFieldValue>,
    amount: MutableState<TextFieldValue>,
    enabled: Boolean = true,
    onCreate: () -> Unit
) {
    Column(
        Modifier
            .padding(vertical = 15.dp, horizontal = 20.dp)
            .padding(vertical = 5.dp),
    ) {
        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            placeholder = { Text(text = "Title") },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
        )
        TextField(
            value = amount.value,
            onValueChange = { amount.value = it },
            placeholder = { Text(text = "Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
        )
        TextButton(
            colors = ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            onClick = onCreate, enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            Text(text = "Create Income")
        }
    }
}