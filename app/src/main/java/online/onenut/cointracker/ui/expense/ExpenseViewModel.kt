package online.onenut.cointracker.ui.expense

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import online.onenut.cointracker.data.model.Expense
import online.onenut.cointracker.data.repository.ExpenseRepository

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {
    val expenses: Flow<List<Expense>> = repository.allExpenses

    fun createExpense(expense: Expense) {
        // expenses.value = expenses.value?.plus(expense)
        //repository.createExpense(expense)
        // getExpenses()
        // return expenses?.contains(expense) ?: false
    }

//    fun getExpense(id: Long): Expense? {
//        return expenses.value?.singleOrNull { it.ID == id }
//    }

//    fun deleteExpense(ID: Long): Boolean {
//        getExpense(ID)?.let { repository.deleteExpense(it) }
//        expenses.value = expenses.value?.filter { it.ID != ID }
//        getExpenses()
//        return !contains(ID)
//    }

//    fun getExpenses(): List<Expense> {
//        repository.getExpenses()
//        expenses.value = repository.allExpenses.value
//        return expenses.value?.toList() ?: listOf()
//    }
//
//    fun contains(ID: Long): Boolean {
//        return expenses.value?.singleOrNull { it.ID == ID } != null
//    }
}