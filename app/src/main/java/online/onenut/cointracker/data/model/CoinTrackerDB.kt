package online.onenut.cointracker.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import online.onenut.cointracker.strings.DBKeys

@Database(
    entities = [Category::class, Expense::class, Income::class],
    version = 1,
    exportSchema = false
)
abstract class CoinTrackerDB : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun IncomeDao(): IncomeDao
    abstract fun ExpenseDao(): ExpenseDao


    companion object {

        @Volatile
        private var INSTANCE: CoinTrackerDB? = null

        fun getInstance(context: Context): CoinTrackerDB = INSTANCE ?: synchronized(this){ INSTANCE ?: buildDataBase(context).also { INSTANCE = it } }

        private fun buildDataBase(context: Context): CoinTrackerDB {
            return Room.databaseBuilder(context.applicationContext, CoinTrackerDB::class.java, DBKeys.DB_name)
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}