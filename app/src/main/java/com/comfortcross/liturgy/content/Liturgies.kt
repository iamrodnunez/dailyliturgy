package com.comfortcross.liturgy.content

/** One selectable liturgy (a full office rendered on its own page). */
data class LiturgyDef(
    val id: String,
    val title: String,
    val subtitle: String,
    /** A one-line description for the selection list. */
    val summary: String,
    val blocks: List<Block>,
)

/** The liturgies offered under the "Liturgies" tab. */
object Liturgies {

    val all: List<LiturgyDef> = listOf(
        LiturgyDef(
            id = "morning",
            title = "Morning Prayer",
            subtitle = "The Daily Office",
            summary = "A short order for the start of the day, with today's psalm and reading.",
            blocks = FieldGuide.morningBlocks,
        ),
        LiturgyDef(
            id = "evening",
            title = "Evening Prayer",
            subtitle = "The Daily Office",
            summary = "An order for the close of day, with the ancient evening hymn Phos Hilaron.",
            blocks = FieldGuide.eveningBlocks,
        ),
        LiturgyDef(
            id = "zahnd",
            title = MorningPrayer.title,
            subtitle = MorningPrayer.subtitle,
            summary = "A fuller morning liturgy after the Rev. Brian Zahnd, offered freely for use.",
            blocks = MorningPrayer.blocks,
        ),
    )

    fun byId(id: String?): LiturgyDef? = all.firstOrNull { it.id == id }
}
