package online.onenut.cointracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import online.onenut.cointracker.strings.DBKeys

@Entity(
    tableName = DBKeys.income_table,
//    foreignKeys = [ForeignKey(
//        entity = Category::class,
//        parentColumns = ["category_id"],
//        childColumns = ["category_id"],
//        onDelete = ForeignKey.CASCADE
//    )]
)
data class Income(
    @ColumnInfo(name = "income_id")
    @PrimaryKey(autoGenerate = true)
    val ID: Long = 0,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "amount") val amount: Double? = 0.0,
//    @ColumnInfo(name = "category_id") val category: Category? = null,
//    @Ignore val date: LocalDate = LocalDate.now(),
//    @Ignore val createdDate: LocalDate = LocalDate.now(),
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "isDirty") val isDirty: Boolean = false,
//    @Ignore val lastUpdated: LocalDate = LocalDate.now()
)