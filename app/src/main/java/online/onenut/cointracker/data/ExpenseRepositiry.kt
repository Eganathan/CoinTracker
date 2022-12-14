package online.onenut.cointracker.data

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import online.onenut.cointracker.data.model.Expense
import online.onenut.cointracker.data.model.ExpenseDao

class ExpenseRepositiry(private val expenseDao: ExpenseDao) {
    val allExpenses: MutableLiveData<List<Expense>> = MutableLiveData()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun getExpenses() {
        coroutineScope.launch(Dispatchers.IO) {
            allExpenses.postValue(expenseDao.getExpenses())
        }
    }

    fun getExpense(id: Long): Flow<Expense?> = expenseDao.getExpense(id)

    fun createExpense(expense: Expense) =
        coroutineScope.launch(Dispatchers.IO) { expenseDao.createExpense(expense) }

    fun updateExpense(expense: Expense) =
        coroutineScope.launch(Dispatchers.IO) { expenseDao.updateExpense(expense) }

    fun deleteExpense(expense: Expense) =
        coroutineScope.launch(Dispatchers.IO) { expenseDao.deleteExpense(expense = expense) }


}