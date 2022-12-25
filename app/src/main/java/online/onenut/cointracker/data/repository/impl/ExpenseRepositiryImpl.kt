package online.onenut.cointracker.data.repository.impl

import kotlinx.coroutines.flow.Flow
import online.onenut.cointracker.data.dao.ExpenseDao
import online.onenut.cointracker.data.model.Expense
import online.onenut.cointracker.data.repository.ExpenseRepository

class ExpenseRepositiryImpl(private val expenseDao: ExpenseDao) : ExpenseRepository {
    override var allExpenses: Flow<List<Expense>> = expenseDao.getExpenses()

    override suspend fun getExpenses(): Flow<List<Expense>> {
        allExpenses = expenseDao.getExpenses()
        return expenseDao.getExpenses()
    }

    override suspend fun getExpense(id: Long): Expense? = expenseDao.getExpense(id)

    override suspend fun createExpense(expense: Expense) {
        expenseDao.createExpense(expense)
    }

    override suspend fun updateExpense(expense: Expense) {
        expenseDao.updateExpense(expense)
    }

    override suspend fun deleteExpense(expense: Expense) {
        expenseDao.deleteExpense(expense = expense)
    }


}