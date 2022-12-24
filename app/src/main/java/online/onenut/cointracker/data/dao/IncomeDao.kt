package online.onenut.cointracker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import online.onenut.cointracker.data.model.Income

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
    fun getIncome(id: Long): Income

}