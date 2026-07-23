package com.comfortcross.liturgy.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.comfortcross.liturgy.data.AppointedReadings
import com.comfortcross.liturgy.data.LectionaryRepository
import com.comfortcross.liturgy.data.LiturgicalCalendar
import com.comfortcross.liturgy.data.LiturgicalDay
import com.comfortcross.liturgy.data.model.DailyEntry
import com.comfortcross.liturgy.data.model.LectionaryMeta
import java.time.LocalDate

data class DailyOffice(
    val today: LocalDate,
    val liturgical: LiturgicalDay,
    /** Today's own daily-lectionary readings (the app's primary reading). */
    val daily: DailyEntry?,
    /** The week's appointed Sunday/holy-day readings, for context. */
    val appointed: AppointedReadings?,
    val meta: LectionaryMeta,
    val dailyMeta: LectionaryMeta,
    val inCoverage: Boolean,
)

@Composable
fun rememberDailyOffice(date: LocalDate = LocalDate.now()): DailyOffice {
    val context = LocalContext.current
    return remember(date) {
        val repo = LectionaryRepository.get(context)
        DailyOffice(
            today = date,
            liturgical = LiturgicalCalendar.resolve(date),
            daily = repo.dailyFor(date),
            appointed = repo.appointedFor(date),
            meta = repo.meta,
            dailyMeta = repo.dailyMeta,
            inCoverage = repo.inCoverage(date),
        )
    }
}
