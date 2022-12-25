package online.onenut.cointracker.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import online.onenut.cointracker.data.model.Expense

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createExpense(expense: Expense)

    @Update
    fun updateExpense(expense: Expense)

    @Delete
    fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM expenses_table")
    fun getExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expenses_table WHERE expense_id == :id")
    fun getExpense(id: Long): Expense?

}