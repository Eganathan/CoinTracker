package online.onenut.cointracker.ui.income.states

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import online.onenut.cointracker.data.model.Income
import online.onenut.cointracker.ui.income.IncomeViewModel


class IncomeListState(
    val viewModel: IncomeViewModel,
    val scope: CoroutineScope
) {
    val incomes = viewModel.allIncome

    fun createIncomes(income: Income) {
        scope.launch {
            viewModel.createIncome(income)
        }
    }
}