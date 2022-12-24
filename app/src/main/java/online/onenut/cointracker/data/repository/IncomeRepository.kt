package online.onenut.cointracker.data.repository

import kotlinx.coroutines.flow.Flow
import online.onenut.cointracker.data.model.Income

interface IncomeRepository {
    val allIncomes: Flow<List<Income>>

    suspend fun getIncomes(): Flow<List<Income>>
    suspend fun getIncome(id: Long): Income?
    suspend fun createIncome(income: Income)
    suspend fun updateIncome(income: Income)
    suspend fun deleteIncome(income: Income)

}