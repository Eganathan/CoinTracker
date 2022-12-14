package online.onenut.cointracker.ui.expense

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import online.onenut.cointracker.data.model.Expense
import online.onenut.cointracker.data.model.ExpenseDao
import online.onenut.cointracker.ui.BaseViewModel

class ExpensesVM(private val expenseDao: ExpenseDao) : ViewModel() {
    val expenses: MutableState<List<Expense>> = mutableStateOf(listOf())

    fun createExpense(expense: Expense): Boolean {
        expenses.value = expenses.value.plus(expense)
        return expenses.value.contains(expense)
    }

    fun getExpense(id: Long): Expense? {
        return expenses.value.singleOrNull { it.ID == id }
    }

    fun deleteExpense(ID: Long): Boolean {
        expenses.value = expenses.value.filter { it.ID != ID }
        return !contains(ID)
    }

    fun getExpenses(): List<Expense> {
        return expenses.value.toList()
    }

    fun contains(ID: Long): Boolean {
        return expenses.value.singleOrNull { it.ID == ID } != null
    }
}