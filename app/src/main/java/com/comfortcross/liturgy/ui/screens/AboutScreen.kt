package com.comfortcross.liturgy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.comfortcross.liturgy.data.ScriptureLinks
import com.comfortcross.liturgy.ui.ComfortCross
import com.comfortcross.liturgy.ui.DailyOffice
import com.comfortcross.liturgy.ui.components.LinkChip

@Composable
fun AboutScreen(office: DailyOffice, contentPadding: PaddingValues) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(contentPadding)
            .padding(horizontal = 22.dp),
    ) {
        Column(
            Modifier.fillMaxWidth().padding(vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ComfortCross(modifier = Modifier.size(72.dp))
            Spacer(Modifier.height(12.dp))
            Text("Comfort Cross", style = MaterialTheme.typography.headlineMedium)
            Text(
                "Daily liturgy for morning prayer",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )
        }

        Card(
            title = "Morning Prayer",
            body = "The order and several prayers follow the Rev. Brian Zahnd's " +
                "\"A Liturgy for Morning Prayer,\" which he offers freely for use. Recited " +
                "texts — the Apostles' Creed, the Lord's Prayer, and the psalms — are given " +
                "in their traditional public-domain wording.",
        )
        Card(
            title = "The Readings",
            body = "Each day shows its own reading from the Revised Common Lectionary Daily " +
                "Readings — the complementary track in the Season after Pentecost, and the " +
                "single daily track in the other seasons. Passages open in the NRSVue via Bible " +
                "Gateway. Bundled coverage: ${office.dailyMeta.coverageStart} to " +
                "${office.dailyMeta.coverageEnd}.",
            link = "dailyLectio.net" to ScriptureLinks.DAILY_READINGS_URL,
        )
        Card(
            title = "Field Guide",
            body = "The Daily Prayer Field Guide is an order for Morning and Evening Prayer in " +
                "the historic Daily Office tradition. Its texts — the confession, canticles, " +
                "creed, Lord's Prayer, collects, and suffrages — are given in their traditional " +
                "public-domain wording. It is not a reproduction of any copyrighted guide.",
        )
        Card(
            title = "Historical Prayers",
            body = "The Prayers section gathers prayers of the saints through the ages — " +
                "Augustine, Patrick, Benedict, Francis, Aquinas, Ignatius, Wesley, and others — " +
                "each in its traditional public-domain wording.",
        )
        Card(
            title = "The motif",
            body = "The app takes its name and design from a small handheld olive-wood comfort " +
                "cross — meant to be held quietly in prayer.",
        )
        Spacer(Modifier.height(28.dp))
    }
}

@Composable
private fun Card(title: String, body: String, link: Pair<String, String>? = null) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp),
    ) {
        Column(Modifier.padding(18.dp)) {
            Text(
                title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(Modifier.height(8.dp))
            Text(body, style = MaterialTheme.typography.bodyMedium)
            if (link != null) {
                Spacer(Modifier.height(14.dp))
                LinkChip(text = link.first, url = link.second)
            }
        }
    }
}
