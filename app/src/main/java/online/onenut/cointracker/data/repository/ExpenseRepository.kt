package online.onenut.cointracker.data.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import online.onenut.cointracker.data.model.Expense


interface ExpenseRepository {
    val allExpenses: MutableLiveData<List<Expense>>

    fun getExpenses()
    fun getExpense(id: Long): Flow<Expense?>
    fun createExpense(expense: Expense): Job
    fun updateExpense(expense: Expense): Job
    fun deleteExpense(expense: Expense): Job

}