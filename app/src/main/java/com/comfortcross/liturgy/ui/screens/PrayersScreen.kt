package com.comfortcross.liturgy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.comfortcross.liturgy.content.HistoricalPrayer
import com.comfortcross.liturgy.content.HistoricalPrayers

@Composable
fun PrayersScreen(contentPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
        contentPadding = contentPadding,
    ) {
        item {
            Column(Modifier.fillMaxWidth().padding(top = 18.dp, bottom = 12.dp)) {
                Text(
                    HistoricalPrayers.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    HistoricalPrayers.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        items(HistoricalPrayers.all) { prayer ->
            PrayerCard(prayer)
            Spacer(Modifier.height(14.dp))
        }
        item {
            Text(
                "Prayers of the saints in their traditional public-domain wording.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 6.dp, bottom = 28.dp),
            )
        }
    }
}

@Composable
private fun PrayerCard(prayer: HistoricalPrayer) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(18.dp)) {
            Text(
                prayer.attribution.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.tertiary,
            )
            Spacer(Modifier.height(10.dp))
            prayer.lines.forEach { line ->
                Text(
                    line,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}
