package online.onenut.cointracker.ui.home.state

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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import online.onenut.cointracker.data.model.Expense
import online.onenut.cointracker.data.model.Income
import online.onenut.cointracker.ui.HomeViewModel
import kotlin.math.roundToInt

enum class Type { EXPENSE, INCOME }

class HomeState @OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class) constructor(
    private val viewModel: HomeViewModel,
    val navController: NavController,
    val scope: CoroutineScope,
    val keyboardController: SoftwareKeyboardController?,
    val modalBottomSheetState: ModalBottomSheetState,
    val focusManager: FocusManager,
    val focusRequester: FocusRequester,
    val context: Context
) {

    val showQuickAdd: MutableState<Boolean> = mutableStateOf(false)
    val title = mutableStateOf(TextFieldValue(""))
    val amount = mutableStateOf(TextFieldValue(""))
    val type = mutableStateOf(Type.EXPENSE)

    val expenses = viewModel.allExpenses
    val incomes = viewModel.allIncomes

    val recentlyAdded = expenses

    val totalExp: Flow<Int> = expenses.map { exp ->
        exp.sumOf { it.amount ?: 0.0 }.roundToInt()
    }
    val totalInc: Flow<Int> = incomes.map { inc ->
        inc.sumOf { it.amount ?: 0.0 }.roundToInt()
    }

    fun quickAdd() {
        when (type.value) {
            Type.EXPENSE -> {
                viewModel.createExpense(
                    expense =
                    Expense(
                        title = title.value.text,
                        amount = amount.value.text.toDoubleOrNull() ?: 0.0,
                    )
                )
            }
            Type.INCOME -> {
                viewModel.createIncome(
                    income =
                    Income(
                        title = title.value.text,
                        amount = amount.value.text.toDoubleOrNull() ?: 0.0,
                    )
                )
            }
        }
        title.value = TextFieldValue()
        amount.value = TextFieldValue()
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