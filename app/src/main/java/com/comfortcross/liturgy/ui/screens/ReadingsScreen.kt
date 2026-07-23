package com.comfortcross.liturgy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.comfortcross.liturgy.data.Relation
import com.comfortcross.liturgy.data.ScriptureLinks
import com.comfortcross.liturgy.data.model.DailyEntry
import com.comfortcross.liturgy.data.model.Observance
import com.comfortcross.liturgy.ui.DailyOffice
import com.comfortcross.liturgy.ui.components.LinkChip
import com.comfortcross.liturgy.ui.longDisplay
import com.comfortcross.liturgy.ui.shortDisplay

@Composable
fun ReadingsScreen(office: DailyOffice, contentPadding: PaddingValues) {
    val daily = office.daily
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
        contentPadding = contentPadding,
    ) {
        item {
            Column(Modifier.fillMaxWidth().padding(top = 18.dp, bottom = 8.dp)) {
                Text(
                    "Today's Readings",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    office.dailyMeta.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        if (daily != null) {
            item {
                val track = if (daily.track == "complementary") {
                    "Complementary track"
                } else {
                    "Daily lectionary"
                }
                Text(
                    "${office.today.longDisplay()}  ·  $track",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(bottom = 12.dp),
                )
            }
            item { DailyReadingsView(daily) }
            item { WeekContext(office) }
            item {
                Spacer(Modifier.height(20.dp))
                LinkChip(text = "dailyLectio.net", url = ScriptureLinks.DAILY_READINGS_URL)
                Spacer(Modifier.height(8.dp))
                Text(
                    office.dailyMeta.translationNote + "  Source: " + office.dailyMeta.source + ".",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.height(28.dp))
            }
        } else if (office.appointed != null) {
            // Outside the bundled daily range: fall back to the week's Sunday readings.
            item {
                val relation = when (office.appointed.relation) {
                    Relation.TODAY -> "For today"
                    Relation.UPCOMING -> "For the coming holy day"
                    Relation.RECENT -> "Most recent holy day"
                }
                Text(
                    "$relation · ${office.appointed.matchedDate.longDisplay()}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(bottom = 12.dp),
                )
            }
            item {
                ObservanceView(
                    office.appointed.entry.observances,
                    cycle = office.appointed.primary.cycle,
                )
            }
            item {
                Spacer(Modifier.height(20.dp))
                LinkChip(text = "Vanderbilt lectionary", url = ScriptureLinks.VANDERBILT_URL)
                Spacer(Modifier.height(28.dp))
            }
        } else {
            item { OutOfRange(office) }
        }
    }
}

@Composable
private fun DailyReadingsView(daily: DailyEntry) {
    Column {
        daily.readings.forEach { reading ->
            ReadingCard(reading.role, reading.citation)
            Spacer(Modifier.height(12.dp))
        }
    }
}

/** A quiet pointer to the Sunday the week turns on, for context only. */
@Composable
private fun WeekContext(office: DailyOffice) {
    val appointed = office.appointed ?: return
    val label = when (appointed.relation) {
        Relation.TODAY -> "Today's observance"
        Relation.UPCOMING -> "The coming Sunday · ${appointed.matchedDate.shortDisplay()}"
        Relation.RECENT -> "This past Sunday · ${appointed.matchedDate.shortDisplay()}"
    }
    Spacer(Modifier.height(8.dp))
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                label.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                appointed.primary.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                "The daily readings prepare for and reflect on this day.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ObservanceView(observances: List<Observance>, cycle: String) {
    var selected by remember(observances) { mutableIntStateOf(0) }
    Column {
        if (observances.size > 1) {
            FlowRow(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
                observances.forEachIndexed { i, o ->
                    FilterChip(
                        selected = selected == i,
                        onClick = { selected = i },
                        label = { Text(o.name, style = MaterialTheme.typography.labelLarge) },
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
        }
        val obs = observances[selected]
        Text(
            obs.name,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            "Year ${obs.cycle}",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(14.dp))
        obs.readings.forEach { reading ->
            ReadingCard(reading.role, reading.citation)
            Spacer(Modifier.height(12.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ReadingCard(role: String, citation: String) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                role.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.tertiary,
            )
            Spacer(Modifier.height(6.dp))
            Text(
                citation,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(Modifier.height(12.dp))
            FlowRow(
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
            ) {
                ScriptureLinks.forCitation(citation).forEach { passage ->
                    LinkChip(text = passage.reference, url = passage.url)
                }
            }
        }
    }
}

@Composable
private fun OutOfRange(office: DailyOffice) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(18.dp)) {
            Text(
                "This date is outside the bundled lectionary range " +
                    "(${office.dailyMeta.coverageStart} – ${office.dailyMeta.coverageEnd}).",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(Modifier.height(14.dp))
            LinkChip(text = "Open the daily readings online", url = ScriptureLinks.DAILY_READINGS_URL)
        }
    }
}
