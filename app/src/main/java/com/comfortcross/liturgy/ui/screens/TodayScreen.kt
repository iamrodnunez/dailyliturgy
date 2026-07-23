package com.comfortcross.liturgy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.comfortcross.liturgy.ui.ComfortCross
import com.comfortcross.liturgy.ui.DailyOffice
import com.comfortcross.liturgy.ui.longDisplay
import com.comfortcross.liturgy.ui.shortDisplay

@Composable
fun TodayScreen(
    office: DailyOffice,
    onOpenLiturgies: () -> Unit,
    onOpenReadings: () -> Unit,
    contentPadding: androidx.compose.foundation.layout.PaddingValues,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(contentPadding)
            .padding(horizontal = 20.dp),
    ) {
        Spacer(Modifier.height(16.dp))
        Hero(office)
        Spacer(Modifier.height(20.dp))
        ReadingsPreview(office, onOpenReadings)
        Spacer(Modifier.height(14.dp))
        ActionCard(
            title = "Liturgies",
            subtitle = "Morning Prayer, Evening Prayer & more",
            icon = Icons.Filled.Spa,
            onClick = onOpenLiturgies,
        )
        Spacer(Modifier.height(28.dp))
    }
}

@Composable
private fun Hero(office: DailyOffice) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        ComfortCross(modifier = Modifier.size(84.dp))
        Spacer(Modifier.height(14.dp))
        Text(
            "Comfort Cross",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            office.today.longDisplay(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(10.dp))
        SeasonTag(office)
    }
}

@Composable
private fun SeasonTag(office: DailyOffice) {
    val accent = office.liturgical.season.accent
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(50),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
        ) {
            Box(Modifier.size(10.dp).clip(RoundedCornerShape(50)).background(accent))
            Spacer(Modifier.width(8.dp))
            Text(
                "${office.liturgical.season.display}  ·  Year ${office.liturgical.cycle}",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun ReadingsPreview(office: DailyOffice, onOpenReadings: () -> Unit) {
    val daily = office.daily
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp),
        onClick = onOpenReadings,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(18.dp)) {
            Text(
                "TODAY'S READINGS",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.tertiary,
            )
            Spacer(Modifier.height(8.dp))
            if (daily != null) {
                val track = if (daily.track == "complementary") {
                    "Complementary track"
                } else {
                    "Daily lectionary"
                }
                Text(
                    office.today.shortDisplay(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "$track  ·  Revised Common Lectionary",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.height(12.dp))
                daily.readings.forEach {
                    Text(
                        "· ${it.citation}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                Spacer(Modifier.height(10.dp))
                Text(
                    "Tap to read →",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                )
            } else {
                Text(
                    "Readings for this date are outside the bundled range " +
                        "(${office.dailyMeta.coverageStart} – ${office.dailyMeta.coverageEnd}).",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Tap to open the readings online →",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Composable
private fun ActionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(20.dp),
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(18.dp).fillMaxWidth(),
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.size(46.dp),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
