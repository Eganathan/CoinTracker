package online.onenut.cointracker.data.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Job
import online.onenut.cointracker.data.model.Income

interface IncomeRepository {
    val allIncomes: MutableLiveData<List<Income>>

    fun getIncomes()
    fun getIncome(id: Long): Income?
    fun createIncome(income: Income): Job
    fun updateIncome(income: Income): Job
    fun deleteIncome(income: Income): Job

}