package online.onenut.cointracker.data.impl

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import online.onenut.cointracker.data.dao.IncomeDao
import online.onenut.cointracker.data.model.Income

class IncomeRepositoryImpl(private val incomeDao: IncomeDao) {
    val allIncomes: MutableLiveData<List<Income>> = MutableLiveData()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun getIncomes() {
        coroutineScope.launch(Dispatchers.IO) {
            allIncomes.postValue(incomeDao.getIncomes().value)
        }
    }

    fun getIncome(id: Long): Income? = incomeDao.getIncome(id)

    fun createIncome(income: Income) =
        coroutineScope.launch(Dispatchers.IO) { incomeDao.createIncome(income) }

    fun updateIncome(income: Income) =
        coroutineScope.launch(Dispatchers.IO) { incomeDao.updateIncome(income) }

    fun deleteIncome(income: Income) =
        coroutineScope.launch(Dispatchers.IO) { incomeDao.deleteIncome(income = income) }

}