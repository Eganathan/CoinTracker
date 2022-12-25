package online.onenut.cointracker.data.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import online.onenut.cointracker.data.model.Expense


interface ExpenseRepository {
    val allExpenses: Flow<List<Expense>>

    suspend fun getExpenses() : Flow<List<Expense>>
    suspend fun  getExpense(id: Long): Expense?
    suspend fun  createExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
    suspend fun deleteExpense(expense: Expense)

}