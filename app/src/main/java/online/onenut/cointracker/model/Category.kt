package online.onenut.cointracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "categories_table")
data class Category(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "category_id")
    val ID: Long,
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @Ignore val createdDate: LocalDate = LocalDate.now(),
    @Ignore val lastUpdated: LocalDate = LocalDate.now()
)