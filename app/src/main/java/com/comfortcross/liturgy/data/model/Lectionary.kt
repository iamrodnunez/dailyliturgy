package com.comfortcross.liturgy.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LectionaryFile(
    val meta: LectionaryMeta,
    val days: Map<String, DayEntry>,
)

@Serializable
data class LectionaryMeta(
    val title: String,
    val source: String,
    val sourceUrl: String,
    val translationNote: String,
    val coverageStart: String,
    val coverageEnd: String,
    val generated: String,
)

@Serializable
data class DayEntry(
    val observances: List<Observance>,
)

@Serializable
data class Observance(
    val name: String,
    val cycle: String,
    val readings: List<Reading>,
)

@Serializable
data class Reading(
    val role: String,
    val citation: String,
)

/** The RCL Daily Readings: one appointed set of readings for every calendar day. */
@Serializable
data class DailyReadingsFile(
    val meta: LectionaryMeta,
    val days: Map<String, DailyEntry>,
)

@Serializable
data class DailyEntry(
    /**
     * "complementary" during the Season after Pentecost (the thematic track the app
     * follows); "single" in every other season, where the daily lectionary has one track.
     */
    val track: String,
    val readings: List<Reading>,
)
