package online.onenut.cointracker.ui.income.states

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
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
    val keyboardController: SoftwareKeyboardController?
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