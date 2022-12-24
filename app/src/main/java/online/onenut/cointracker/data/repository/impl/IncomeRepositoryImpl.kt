package online.onenut.cointracker.data.repository.impl

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import online.onenut.cointracker.data.dao.IncomeDao
import online.onenut.cointracker.data.model.Income
import online.onenut.cointracker.data.repository.IncomeRepository

class IncomeRepositoryImpl(private val incomeDao: IncomeDao) : IncomeRepository {
    override val allIncomes: MutableLiveData<List<Income>> = MutableLiveData()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun getIncomes() {
        coroutineScope.launch(Dispatchers.IO) {
            allIncomes.postValue(incomeDao.getIncomes().value)
        }
    }

    override fun getIncome(id: Long): Income? = incomeDao.getIncome(id)

    override fun createIncome(income: Income) =
        coroutineScope.launch(Dispatchers.IO) { incomeDao.createIncome(income) }

    override fun updateIncome(income: Income) =
        coroutineScope.launch(Dispatchers.IO) { incomeDao.updateIncome(income) }

    override fun deleteIncome(income: Income) =
        coroutineScope.launch(Dispatchers.IO) { incomeDao.deleteIncome(income = income) }

}