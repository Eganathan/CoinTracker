package online.onenut.cointracker.data.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createExpense(expense: Expense)

    @Update
    fun updateExpense(expense: Expense)

    @Delete
    fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM expenses_table")
    fun getExpenses(): List<Expense>?

    @Query("SELECT * FROM expenses_table WHERE expense_id == :id")
    fun getExpense(id: Long): Flow<Expense?>

}