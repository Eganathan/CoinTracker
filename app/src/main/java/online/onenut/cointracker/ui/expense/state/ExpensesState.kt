package online.onenut.cointracker.ui.expense.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import online.onenut.cointracker.data.model.Expense
import online.onenut.cointracker.ui.expense.ExpenseViewModel

class ExpensesState(val viewModel: ExpenseViewModel) {
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