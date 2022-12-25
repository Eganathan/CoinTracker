package online.onenut.cointracker.ui.expense.state

import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import kotlinx.coroutines.CoroutineScope
import online.onenut.cointracker.data.model.Expense
import online.onenut.cointracker.ui.expense.ExpenseViewModel

class ExpensesState @OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
) constructor(
    val viewModel: ExpenseViewModel,
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager,
    focusRequester: FocusRequester,
    context: Context
) {
    val expenses: MutableState<List<Expense>> = mutableStateOf(listOf())

    fun getExpenses() {
        expenses.value = viewModel.getExpenses()
        print(expenses.value.size)
    }

    fun createExpense(expense: Expense) {
        viewModel.createExpense(expense)
        getExpenses()
    }
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun rememberExpenseListState(
    viewModel: ExpenseViewModel,
    scope: CoroutineScope = rememberCoroutineScope(),
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = FocusRequester(),
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    modalBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { ModalBottomSheetValue.Expanded != it }),
    context: Context = LocalContext.current
) = remember {
    ExpensesState(
        viewModel = viewModel,
        scope = scope,
        modalBottomSheetState = modalBottomSheetState,
        keyboardController = keyboardController,
        focusManager,
        focusRequester,
        context
    )
}