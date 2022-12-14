package online.onenut.cointracker.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import online.onenut.cointracker.data.ExpenseRepositiry
import online.onenut.cointracker.data.model.Expense
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(private val expenseRepositiry: ExpenseRepositiry) :
    ViewModel() {

    fun getAllExpenses() = expenseRepositiry.getExpenses()
    fun getExpense(id: Long): Flow<Expense?> = expenseRepositiry.getExpense(id)

    fun createExpense(expense: Expense) = expenseRepositiry.createExpense(expense)

    fun updateExpense(expense: Expense) = expenseRepositiry.updateExpense(expense)

    fun deleteExpense(expense: Expense) = expenseRepositiry.deleteExpense(expense = expense)

    val expensesList: LiveData<List<Expense>> = expenseRepositiry.allExpenses

}