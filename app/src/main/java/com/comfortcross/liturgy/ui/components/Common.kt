package com.comfortcross.liturgy.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.comfortcross.liturgy.ui.openUrl

/** A pill that opens a URL in the browser. */
@Composable
fun LinkChip(text: String, url: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Chip(
        text = text,
        icon = Icons.AutoMirrored.Filled.OpenInNew,
        filled = true,
        modifier = modifier,
        onClick = { openUrl(context, url) },
    )
}

/** A pill that triggers an in-app action. */
@Composable
fun TextActionChip(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Chip(
        text = text,
        icon = Icons.AutoMirrored.Filled.MenuBook,
        filled = false,
        modifier = modifier,
        onClick = onClick,
    )
}

@Composable
private fun Chip(
    text: String,
    icon: ImageVector,
    filled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val container = if (filled) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.surface
    val content = if (filled) MaterialTheme.colorScheme.onPrimary
    else MaterialTheme.colorScheme.primary
    Surface(
        color = container,
        contentColor = content,
        shape = RoundedCornerShape(50),
        border = if (filled) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        onClick = onClick,
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(6.dp))
            Text(text, style = MaterialTheme.typography.labelLarge)
        }
    }
}
