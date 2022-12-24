package online.onenut.cointracker.data.repository.impl

import kotlinx.coroutines.flow.Flow
import online.onenut.cointracker.data.dao.IncomeDao
import online.onenut.cointracker.data.model.Income
import online.onenut.cointracker.data.repository.IncomeRepository

class IncomeRepositoryImpl(private val incomeDao: IncomeDao) : IncomeRepository {

    override var allIncomes = incomeDao.getIncomes()

    override suspend fun getIncomes(): Flow<List<Income>> {
        allIncomes = incomeDao.getIncomes()
        return incomeDao.getIncomes()
    }

    override suspend fun getIncome(id: Long): Income? = incomeDao.getIncome(id)

    override suspend fun createIncome(income: Income) {
        incomeDao.createIncome(income)
    }

    override suspend fun updateIncome(income: Income) {
        incomeDao.updateIncome(income)
    }

    override suspend fun deleteIncome(income: Income) {
        incomeDao.deleteIncome(income = income)
    }

}