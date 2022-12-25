package online.onenut.cointracker.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import online.onenut.cointracker.data.model.Expense
import online.onenut.cointracker.data.model.Income
import online.onenut.cointracker.data.repository.impl.ExpenseRepositiryImpl
import online.onenut.cointracker.data.repository.impl.IncomeRepositoryImpl
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val expenseRepositiry: ExpenseRepositiryImpl,
    private val incomeRepositoryImpl: IncomeRepositoryImpl
) :
    ViewModel() {
    val allExpenses = expenseRepositiry.allExpenses
    val allIncomes = incomeRepositoryImpl.allIncomes

//    fun getExpense(id: Long): Flow<Expense?> = expenseRepositiry.getExpense(id)

    fun createExpense(expense: Expense) {
        CoroutineScope(Dispatchers.IO).launch {
            expenseRepositiry.createExpense(expense)
        }
    }

    fun createIncome(income: Income) {
        CoroutineScope(Dispatchers.IO).launch {
            incomeRepositoryImpl.createIncome(income)
        }
    }

//    fun updateExpense(expense: Expense) = expenseRepositiry.updateExpense(expense)
//    fun deleteExpense(expense: Expense) = expenseRepositiry.deleteExpense(expense = expense)
//    val expensesList: LiveData<List<Expense>> = expenseRepositiry.allExpenses

}