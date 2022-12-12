package online.onenut.cointracker.ui.home.state

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import online.onenut.cointracker.data.entity.Expense
import online.onenut.cointracker.ui.expense.ExpensesVM

enum class Type { EXPENSE, INCOME }

class HomeState(val vm: ExpensesVM) {
    val showQuickAdd: MutableState<Boolean> = mutableStateOf(false)
    val title = mutableStateOf(TextFieldValue(""))
    val amount = mutableStateOf(TextFieldValue(""))
    val type = mutableStateOf(Type.EXPENSE)
    val recentlyAdded: MutableState<List<Expense>> = mutableStateOf(listOf())

    fun quickAdd() {
        when (type.value) {
            Type.EXPENSE -> {
                vm.createExpense(
                    Expense(
                        ID = 0,
                        title = title.value.text,
                        amount = amount.value.text.toDoubleOrNull() ?: 0.0,
                    )
                )
            }
            Type.INCOME -> TODO()
        }
        recentlyAdded.value = vm.getExpenses()
        title.value = TextFieldValue()
        amount.value = TextFieldValue()
        Log.e("ADD", "${recentlyAdded.value}")
    }
}
