package online.onenut.cointracker.data.impl

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import online.onenut.cointracker.data.dao.ExpenseDao
import online.onenut.cointracker.data.model.Expense

class ExpenseRepositiryImpl(private val expenseDao: ExpenseDao) : ExpenseRepository {
    override val allExpenses: MutableLiveData<List<Expense>> = MutableLiveData()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun getExpenses() {
        coroutineScope.launch(Dispatchers.IO) {
            allExpenses.postValue(expenseDao.getExpenses())
        }
    }

    override fun getExpense(id: Long): Flow<Expense?> = expenseDao.getExpense(id)

    override fun createExpense(expense: Expense) =
        coroutineScope.launch(Dispatchers.IO) { expenseDao.createExpense(expense) }

    override fun updateExpense(expense: Expense) =
        coroutineScope.launch(Dispatchers.IO) { expenseDao.updateExpense(expense) }

    override fun deleteExpense(expense: Expense) =
        coroutineScope.launch(Dispatchers.IO) { expenseDao.deleteExpense(expense = expense) }


}

interface ExpenseRepository {
    val allExpenses: MutableLiveData<List<Expense>>

    fun getExpenses()
    fun getExpense(id: Long): Flow<Expense?>
    fun createExpense(expense: Expense): Job
    fun updateExpense(expense: Expense): Job
    fun deleteExpense(expense: Expense): Job

}