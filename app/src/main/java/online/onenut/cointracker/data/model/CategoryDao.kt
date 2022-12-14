package online.onenut.cointracker.data.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createExpense(category: Category)

    @Update
    fun updateCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)

    @Query("SELECT * FROM CATEGORIES_TABLE")
    fun getCategorys(): LiveData<List<Category>?>

    @Query("SELECT * FROM CATEGORIES_TABLE WHERE category_id == :id")
    fun getCategory(id: Long): Category?

}