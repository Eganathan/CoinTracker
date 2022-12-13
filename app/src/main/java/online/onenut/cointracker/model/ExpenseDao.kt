package online.onenut.cointracker.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExpenseDao {
    @Insert
    fun createExpense(expense: Expense)

    @Update
    fun updateExpense(expense: Expense)

    @Delete
    fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM EXPENSES_TABLE")
    fun getExpenses(): LiveData<List<Expense>?>

    @Query("SELECT * FROM EXPENSES_TABLE WHERE expense_id == :id")
    fun getExpense(id: Long): Expense?

}