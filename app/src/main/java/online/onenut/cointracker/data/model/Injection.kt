package online.onenut.cointracker.data.model

import android.content.Context

object Injection {

    fun provideExpensesDataSource(context: Context): ExpenseDao {
        return CoinTrackerDB.getInstance(context).ExpenseDao()
    }

    fun provideIncomesDataSource(context: Context): IncomeDao {
        return CoinTrackerDB.getInstance(context).IncomeDao()
    }

    fun provideCategoryDataSource(context: Context): CategoryDao {
        return CoinTrackerDB.getInstance(context).categoryDao()
    }

//    fun provideExpensesViewModelFactory(context: Context): ViewModelFactory {
//        val dataSource = provideUserDataSource(context)
//        return ViewModelFactory(dataSource)
//    }
}