package online.onenut.cointracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import online.onenut.cointracker.strings.DBKeys
import java.time.LocalDate

@Entity(tableName = DBKeys.category_table)
data class Category(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "category_id")
    val ID: Long,
    val name: String? = null,
    val description: String? = null,
//    @Ignore val createdDate: LocalDate = LocalDate.now(),
//    @Ignore val lastUpdated: LocalDate = LocalDate.now()
)