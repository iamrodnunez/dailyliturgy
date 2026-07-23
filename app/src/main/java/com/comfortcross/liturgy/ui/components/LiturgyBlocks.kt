package com.comfortcross.liturgy.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.comfortcross.liturgy.content.Block
import com.comfortcross.liturgy.content.DayReadingKind
import com.comfortcross.liturgy.data.ScriptureLinks
import com.comfortcross.liturgy.data.model.DailyEntry
import com.comfortcross.liturgy.data.psalmReference
import com.comfortcross.liturgy.data.readingReference

fun LazyListScope.liturgy(
    blocks: List<Block>,
    daily: DailyEntry?,
    onOpenReadings: () -> Unit,
) {
    items(blocks) { block ->
        when (block) {
            is Block.Section -> SectionBlock(block)
            is Block.Rubric -> RubricBlock(block.text)
            is Block.Response -> ResponseBlock(block)
            is Block.Prose -> ProseBlock(block)
            is Block.Lines -> LinesBlock(block.lines)
            is Block.Recite -> ReciteBlock(block)
            is Block.DayReading -> DayReadingBlock(block.kind, daily, onOpenReadings)
            is Block.Attribution -> AttributionBlock(block.text)
        }
    }
}

@Composable
private fun SectionBlock(block: Block.Section) {
    Column(Modifier.fillMaxWidth().padding(top = 26.dp, bottom = 6.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(2.dp),
                modifier = Modifier.width(22.dp).height(3.dp),
            ) {}
            Spacer(Modifier.width(10.dp))
            Text(
                text = block.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        block.rubric?.let {
            Spacer(Modifier.height(4.dp))
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun RubricBlock(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
    )
}

@Composable
private fun ResponseBlock(block: Block.Response) {
    Column(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(block.leader, style = MaterialTheme.typography.bodyLarge)
        Text(
            block.response,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
private fun ProseBlock(block: Block.Prose) {
    Column(Modifier.fillMaxWidth().padding(vertical = 4.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        block.paragraphs.forEach {
            Text(it, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
private fun LinesBlock(lines: List<String>) {
    Column(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        lines.forEach {
            Text(it, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
private fun ReciteBlock(block: Block.Recite) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                block.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
            Spacer(Modifier.height(8.dp))
            block.lines.forEach {
                Text(it, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
private fun DayReadingBlock(
    kind: DayReadingKind,
    daily: DailyEntry?,
    onOpenReadings: () -> Unit,
) {
    val label = if (kind == DayReadingKind.PSALM) "Psalm for the day" else "Reading for the day"
    val reference = when (kind) {
        DayReadingKind.PSALM -> daily?.psalmReference()
        DayReadingKind.GOSPEL -> daily?.readingReference()
    }
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                label.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(6.dp))
            if (reference != null) {
                Text(reference, style = MaterialTheme.typography.headlineSmall)
                Spacer(Modifier.height(10.dp))
                Row {
                    LinkChip(text = "Read (NRSVue)", url = ScriptureLinks.bibleGatewayUrl(reference))
                    Spacer(Modifier.width(8.dp))
                    TextActionChip(text = "All readings", onClick = onOpenReadings)
                }
            } else {
                Text(
                    "Turn to the appointed readings for today.",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.height(8.dp))
                TextActionChip(text = "Open readings", onClick = onOpenReadings)
            }
        }
    }
}

@Composable
private fun AttributionBlock(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth().padding(top = 28.dp, bottom = 8.dp),
    )
}
