package com.comfortcross.liturgy.data

import com.comfortcross.liturgy.data.model.DailyEntry
import com.comfortcross.liturgy.data.model.Observance

/** First psalm reference appointed for the day (handles the two-track Ordinary Time form). */
fun Observance.psalmReference(): String? {
    readings.forEach { r ->
        ScriptureLinks.forCitation(r.citation).forEach { p ->
            if (p.reference.startsWith("Psalm", ignoreCase = true)) return p.reference
        }
    }
    return null
}

/** The Gospel reference appointed for the day. */
fun Observance.gospelReference(): String? {
    val gospel = readings.firstOrNull { it.role.contains("Gospel", ignoreCase = true) }
        ?: return null
    return ScriptureLinks.forCitation(gospel.citation).firstOrNull()?.reference
        ?: gospel.citation
}

/** The psalm appointed for today in the daily lectionary. */
fun DailyEntry.psalmReference(): String? =
    readings.firstOrNull { it.role.equals("Psalm", ignoreCase = true) }?.citation
        ?: readings.firstOrNull {
            ScriptureLinks.forCitation(it.citation)
                .any { p -> p.reference.startsWith("Psalm", ignoreCase = true) }
        }?.citation

/**
 * The reading to sit with in the daily office: the Gospel if the day appoints one,
 * otherwise the last (typically New Testament) reading of the day.
 */
fun DailyEntry.readingReference(): String? =
    readings.firstOrNull { it.role.contains("Gospel", ignoreCase = true) }?.citation
        ?: readings.lastOrNull { !it.role.equals("Psalm", ignoreCase = true) }?.citation
