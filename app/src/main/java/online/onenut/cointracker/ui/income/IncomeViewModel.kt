package online.onenut.cointracker.ui.income

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import online.onenut.cointracker.data.model.Income
import online.onenut.cointracker.data.repository.IncomeRepository

class IncomeViewModel(private val repository: IncomeRepository) : ViewModel() {
    val allIncome = repository.allIncomes

    suspend fun createIncome(income: Income) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.createIncome(income)
        }
    }

}
