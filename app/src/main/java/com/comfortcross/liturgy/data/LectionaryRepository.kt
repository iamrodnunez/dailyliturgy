package com.comfortcross.liturgy.data

import android.content.Context
import com.comfortcross.liturgy.data.model.DailyEntry
import com.comfortcross.liturgy.data.model.DailyReadingsFile
import com.comfortcross.liturgy.data.model.DayEntry
import com.comfortcross.liturgy.data.model.LectionaryFile
import com.comfortcross.liturgy.data.model.LectionaryMeta
import com.comfortcross.liturgy.data.model.Observance
import kotlinx.serialization.json.Json
import java.time.LocalDate

/** Relationship of the matched lectionary day to the day the user opened the app. */
enum class Relation { TODAY, UPCOMING, RECENT }

data class AppointedReadings(
    val matchedDate: LocalDate,
    val relation: Relation,
    val entry: DayEntry,
) {
    /** Default observance to show first (seasonal Sunday preferred over optional feasts). */
    val primary: Observance get() = entry.observances.first()
    val alternates: List<Observance> get() = entry.observances.drop(1)
}

class LectionaryRepository private constructor(
    private val file: LectionaryFile,
    private val daily: DailyReadingsFile,
) {
    val meta: LectionaryMeta get() = file.meta
    val dailyMeta: LectionaryMeta get() = daily.meta

    private val coverageStart = LocalDate.parse(file.meta.coverageStart)
    private val coverageEnd = LocalDate.parse(file.meta.coverageEnd)

    fun inCoverage(date: LocalDate): Boolean =
        !date.isBefore(coverageStart) && !date.isAfter(coverageEnd)

    /** The RCL daily readings appointed for [date] itself, if bundled. */
    fun dailyFor(date: LocalDate): DailyEntry? =
        daily.days[date.toString()]?.takeIf { it.readings.isNotEmpty() }

    /**
     * The readings appointed for [date]. If [date] itself has no observance (an ordinary
     * weekday), fall back to the coming Sunday/holy day the week is oriented toward, and
     * failing that the most recent one.
     */
    fun appointedFor(date: LocalDate): AppointedReadings? {
        file.days[date.toString()]?.let {
            return AppointedReadings(date, Relation.TODAY, it)
        }
        // Look forward up to a week for the upcoming Sunday / holy day.
        for (i in 1..8) {
            val d = date.plusDays(i.toLong())
            file.days[d.toString()]?.let {
                return AppointedReadings(d, Relation.UPCOMING, it)
            }
        }
        // Otherwise the most recent one within a week.
        for (i in 1..8) {
            val d = date.minusDays(i.toLong())
            file.days[d.toString()]?.let {
                return AppointedReadings(d, Relation.RECENT, it)
            }
        }
        return null
    }

    companion object {
        @Volatile private var instance: LectionaryRepository? = null
        private val json = Json { ignoreUnknownKeys = true }

        fun get(context: Context): LectionaryRepository =
            instance ?: synchronized(this) {
                instance ?: load(context).also { instance = it }
            }

        private fun load(context: Context): LectionaryRepository {
            val text = context.assets.open("lectionary.json")
                .bufferedReader().use { it.readText() }
            val dailyText = context.assets.open("daily_readings.json")
                .bufferedReader().use { it.readText() }
            return LectionaryRepository(
                json.decodeFromString(LectionaryFile.serializer(), text),
                json.decodeFromString(DailyReadingsFile.serializer(), dailyText),
            )
        }
    }
}
