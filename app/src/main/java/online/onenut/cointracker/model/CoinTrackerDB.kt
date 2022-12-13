package online.onenut.cointracker.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Category::class, Expense::class, Income::class], version = 1)
abstract class CoinTrackerDB : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun IncomeDao(): IncomeDao
    abstract fun ExpenseDao(): ExpenseDao

    @Volatile
    private var instance: CoinTrackerDB? = null

    fun getInstances(context: Context): CoinTrackerDB? {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                CoinTrackerDB::class.java, "coin_tracker_db"
            )
                .fallbackToDestructiveMigration()
                .build()

        }
        return instance
    }

}