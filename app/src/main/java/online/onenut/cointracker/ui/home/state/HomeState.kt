package online.onenut.cointracker.ui.home.state

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import online.onenut.cointracker.data.model.Expense
import online.onenut.cointracker.ui.BaseViewModel

enum class Type { EXPENSE, INCOME }

class HomeState(val vm: BaseViewModel) {
    val showQuickAdd: MutableState<Boolean> = mutableStateOf(false)
    val title = mutableStateOf(TextFieldValue(""))
    val amount = mutableStateOf(TextFieldValue(""))
    val type = mutableStateOf(Type.EXPENSE)

    val recentlyAdded: LiveData<List<Expense>> = vm.expensesList

    fun quickAdd() {
        when (type.value) {
            Type.EXPENSE -> {
//                vm.createExpense(
                vm.createExpense(
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
        vm.getAllExpenses()
    }
}
