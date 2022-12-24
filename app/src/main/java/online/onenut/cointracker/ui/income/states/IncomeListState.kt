package online.onenut.cointracker.ui.income.states

import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import online.onenut.cointracker.data.model.Income
import online.onenut.cointracker.ui.income.IncomeViewModel


class IncomeListState @OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
) constructor(
    val viewModel: IncomeViewModel,
    val scope: CoroutineScope,
    val modalBottomSheetState: ModalBottomSheetState,
    val keyboardController: SoftwareKeyboardController?,
    val focusManager: FocusManager,
    val focusRequester: FocusRequester,
    val context: Context
) {
    val incomes = viewModel.allIncome

    val titleTF = mutableStateOf(TextFieldValue())
    val descriptionTF = mutableStateOf(TextFieldValue())
    val amountTF = mutableStateOf(TextFieldValue())

    @OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
    fun createIncomes() {
        scope.launch {
            viewModel.createIncome(
                income = Income(
                    title = titleTF.value.text,
                    amount = amountTF.value.text.toDoubleOrNull() ?: 0.0
                )
            )
            titleTF.value = TextFieldValue()
            amountTF.value = TextFieldValue()
            modalBottomSheetState.hide()
            keyboardController?.hide()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun rememberIncomeListState(
    viewModel: IncomeViewModel,
    scope: CoroutineScope = rememberCoroutineScope(),
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = FocusRequester(),
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    modalBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { ModalBottomSheetValue.Expanded != it }),
    context: Context = LocalContext.current
) = remember {
    IncomeListState(
        viewModel = viewModel,
        scope = scope,
        modalBottomSheetState = modalBottomSheetState,
        keyboardController = keyboardController,
        focusManager,
        focusRequester,
        context
    )
}