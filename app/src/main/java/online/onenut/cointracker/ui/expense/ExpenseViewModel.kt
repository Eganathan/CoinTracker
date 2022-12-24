package online.onenut.cointracker.ui.expense

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import online.onenut.cointracker.data.model.Expense
import online.onenut.cointracker.data.dao.ExpenseDao
import online.onenut.cointracker.data.repository.ExpenseRepository

class ExpenseViewModel(private val repository: ExpenseRepository):ViewModel(){
    val expenses: MutableState<List<Expense>?> = mutableStateOf(null)

    fun createExpense(expense: Expense): Boolean {
        expenses.value = expenses.value?.plus(expense)
        repository.createExpense(expense)
        getExpenses()
        return expenses.value?.contains(expense) ?: false
    }

    fun getExpense(id: Long): Expense? {
        return expenses.value?.singleOrNull { it.ID == id }
    }

    fun deleteExpense(ID: Long): Boolean {
        getExpense(ID)?.let { repository.deleteExpense(it) }
        expenses.value = expenses.value?.filter { it.ID != ID }
        getExpenses()
        return !contains(ID)
    }

    fun getExpenses(): List<Expense> {
        repository.getExpenses()
        expenses.value = repository.allExpenses.value
        return expenses.value?.toList() ?: listOf()
    }

    fun contains(ID: Long): Boolean {
        return expenses.value?.singleOrNull { it.ID == ID } != null
    }
}