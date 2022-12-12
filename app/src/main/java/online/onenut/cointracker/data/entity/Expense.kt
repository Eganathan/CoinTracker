package online.onenut.cointracker.data.entity

import java.time.LocalDate

data class Expense(
    val ID: Long,
    val title: String? = null,
    val amount: Double? = 0.0,
    val date: LocalDate = LocalDate.now(),
    val createdDate: LocalDate = LocalDate.now(),
    val description: String? = null,
    val isDirty: Boolean = false,
    val lastUpdated: LocalDate = LocalDate.now()
)