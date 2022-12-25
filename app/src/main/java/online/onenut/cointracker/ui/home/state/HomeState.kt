package online.onenut.cointracker.ui.home.state

import android.content.Context
import android.util.Log
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import online.onenut.cointracker.data.model.Expense
import online.onenut.cointracker.ui.HomeViewModel

enum class Type { EXPENSE, INCOME }

class HomeState @OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class) constructor(
    private val viewModel: HomeViewModel,
    val navController: NavController,
    scope: CoroutineScope,
    keyboardController: SoftwareKeyboardController?,
    modalBottomSheetState: ModalBottomSheetState,
    focusManager: FocusManager,
    focusRequester: FocusRequester,
    context: Context
) {
    val showQuickAdd: MutableState<Boolean> = mutableStateOf(false)
    val title = mutableStateOf(TextFieldValue(""))
    val amount = mutableStateOf(TextFieldValue(""))
    val type = mutableStateOf(Type.EXPENSE)

    val recentlyAdded: LiveData<List<Expense>> = viewModel.expensesList

    fun quickAdd() {
        when (type.value) {
            Type.EXPENSE -> {
//                vm.createExpense(
                viewModel.createExpense(
                    expense =
                    Expense(
                        ID = 0,
                        title = title.value.text,
                        amount = amount.value.text.toDoubleOrNull() ?: 0.0,
                    )
                )
            }
            Type.INCOME -> TODO()
        }
        title.value = TextFieldValue()
        amount.value = TextFieldValue()
        getExpenses()
        Log.e("ADD", "${recentlyAdded.value?.size}")
    }

    fun getExpenses() {
        viewModel.getAllExpenses()
    }
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun rememberHomeState(
    viewModel: HomeViewModel,
    navController: NavController,
    scope: CoroutineScope = rememberCoroutineScope(),
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = FocusRequester(),
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    modalBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { ModalBottomSheetValue.Expanded != it }),
    context: Context = LocalContext.current
) = remember {
    HomeState(
        viewModel = viewModel,
        navController = navController,
        scope = scope,
        modalBottomSheetState = modalBottomSheetState,
        keyboardController = keyboardController,
        focusManager = focusManager,
        focusRequester = focusRequester,
        context = context
    )
}