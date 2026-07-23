package com.comfortcross.liturgy.data

import androidx.compose.ui.graphics.Color
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

/** Liturgical season, with a quiet accent color that fits the olive-wood palette. */
enum class Season(val display: String, val accent: Color) {
    ADVENT("Advent", Color(0xFF5B5F8A)),          // muted violet-blue
    CHRISTMAS("Christmastide", Color(0xFFB08A3E)), // warm gold
    EPIPHANY("Epiphany", Color(0xFF6E7F52)),       // olive green (ordinary)
    LENT("Lent", Color(0xFF7A5B72)),               // muted plum
    HOLY_WEEK("Holy Week", Color(0xFF8A4B45)),     // deep red-brown
    EASTER("Eastertide", Color(0xFFC9A24B)),       // bright gold
    PENTECOST("Day of Pentecost", Color(0xFF9B4A3F)), // red
    ORDINARY("Season after Pentecost", Color(0xFF6E7F52)), // olive green
}

data class LiturgicalDay(
    val date: LocalDate,
    val season: Season,
    /** Lectionary cycle year: "A", "B", or "C". */
    val cycle: String,
    /** The civil year in which the current liturgical year's Advent began. */
    val liturgicalYearStart: Int,
)

object LiturgicalCalendar {

    /** Western (Gregorian) computus — Easter Sunday for a civil year. */
    fun easter(year: Int): LocalDate {
        val a = year % 19
        val b = year / 100
        val c = year % 100
        val d = b / 4
        val e = b % 4
        val f = (b + 8) / 25
        val g = (b - f + 1) / 3
        val h = (19 * a + b - d - g + 15) % 30
        val i = c / 4
        val k = c % 4
        val l = (32 + 2 * e + 2 * i - h - k) % 7
        val m = (a + 11 * h + 22 * l) / 451
        val month = (h + l - 7 * m + 114) / 31
        val day = ((h + l - 7 * m + 114) % 31) + 1
        return LocalDate.of(year, month, day)
    }

    /** First Sunday of Advent for the liturgical year that leads into [civilYear]'s Christmas. */
    fun firstSundayOfAdvent(civilYear: Int): LocalDate {
        // 4th Sunday of Advent is the Sunday on or before Dec 24; Advent I is three weeks earlier.
        val dec24 = LocalDate.of(civilYear, 12, 24)
        val advent4 = dec24.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        return advent4.minusWeeks(3)
    }

    /** Resolve the liturgical day (season + lectionary cycle) for any date. */
    fun resolve(date: LocalDate): LiturgicalDay {
        val year = date.year
        val adventThisYear = firstSundayOfAdvent(year)
        val liturgicalYearStart = if (!date.isBefore(adventThisYear)) year else year - 1

        // Cycle A/B/C keyed to the civil year in which Advent began.
        val cycle = when (Math.floorMod(liturgicalYearStart, 3)) {
            0 -> "A"
            1 -> "B"
            else -> "C"
        }

        val season = seasonFor(date, liturgicalYearStart)
        return LiturgicalDay(date, season, cycle, liturgicalYearStart)
    }

    private fun seasonFor(date: LocalDate, lyStart: Int): Season {
        val adventStart = firstSundayOfAdvent(lyStart)
        val christmas = LocalDate.of(lyStart, 12, 25)
        val nextCivilYear = lyStart + 1
        val epiphany = LocalDate.of(nextCivilYear, 1, 6)
        val easter = easter(nextCivilYear)
        val ashWednesday = easter.minusDays(46)
        val palmSunday = easter.minusDays(7)
        val pentecost = easter.plusDays(49)
        val nextAdvent = firstSundayOfAdvent(nextCivilYear)

        return when {
            date < adventStart -> Season.ORDINARY // shouldn't happen for a well-formed range
            date < christmas -> Season.ADVENT
            date < epiphany -> Season.CHRISTMAS
            date < ashWednesday -> Season.EPIPHANY
            date < palmSunday -> Season.LENT
            date < easter -> Season.HOLY_WEEK
            date == pentecost -> Season.PENTECOST
            date < pentecost -> Season.EASTER
            date < nextAdvent -> Season.ORDINARY
            else -> Season.ADVENT
        }
    }
}
