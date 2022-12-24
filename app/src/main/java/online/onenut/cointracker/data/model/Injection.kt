package online.onenut.cointracker.data.model

import android.content.Context
import online.onenut.cointracker.data.dao.CategoryDao
import online.onenut.cointracker.data.dao.ExpenseDao
import online.onenut.cointracker.data.dao.IncomeDao

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