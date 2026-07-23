package com.comfortcross.liturgy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.comfortcross.liturgy.content.Block
import com.comfortcross.liturgy.data.model.DailyEntry
import com.comfortcross.liturgy.ui.ComfortCross
import com.comfortcross.liturgy.ui.components.liturgy

@Composable
fun LiturgyScreen(
    title: String,
    subtitle: String,
    blocks: List<Block>,
    daily: DailyEntry?,
    onOpenReadings: () -> Unit,
    contentPadding: PaddingValues,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 22.dp),
        contentPadding = contentPadding,
    ) {
        item {
            Column(
                Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ComfortCross(modifier = Modifier.size(56.dp))
                Spacer(Modifier.height(12.dp))
                Text(
                    title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                )
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                )
            }
        }
        liturgy(blocks, daily, onOpenReadings)
        item { Spacer(Modifier.height(32.dp)) }
    }
}
