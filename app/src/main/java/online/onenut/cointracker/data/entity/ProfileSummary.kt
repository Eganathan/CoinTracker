package online.onenut.cointracker.data.entity

import java.time.LocalDate

data class ProfileSummary(
    val netValue: Double,
    val cMonthIncome: Double,
    val cMonthExpense: Double,
    val cYearIncome: Double,
    val cYearExpense: Double,
    val lastUpdated: LocalDate,
    val isDirty: Boolean
)
