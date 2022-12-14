package online.onenut.cointracker.data.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createIncome(income: Income)

    @Update
    fun updateIncome(income: Income)

    @Delete
    fun deleteIncome(income: Income)

    @Query("SELECT * FROM INCOME_TABLE")
    fun getIncomes(): LiveData<List<Income>?>

    @Query("SELECT * FROM INCOME_TABLE WHERE income_id == :id")
    fun getIncome(id: Long): Income?

}