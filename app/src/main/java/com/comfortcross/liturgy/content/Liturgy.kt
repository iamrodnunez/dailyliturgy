package com.comfortcross.liturgy.content

/** A pull from the day's appointed lectionary reading, rendered inline in a liturgy. */
enum class DayReadingKind { PSALM, GOSPEL }

/** The renderable pieces of a liturgy, in order. */
sealed interface Block {
    /** A titled movement of the liturgy, optionally with a small instruction. */
    data class Section(val title: String, val rubric: String? = null) : Block

    /** An italic instruction to the one praying. */
    data class Rubric(val text: String) : Block

    /** A versicle-and-response: leader line, then the gathered response. */
    data class Response(val leader: String, val response: String) : Block

    /** A prayer said as flowing prose (one or more paragraphs). */
    data class Prose(val paragraphs: List<String>) : Block

    /** A prayer or text set line by line (confession, canticle, psalm). */
    data class Lines(val lines: List<String>) : Block

    /** A recited text the app supplies in full (creed, Lord's Prayer, a psalm). */
    data class Recite(val title: String, val lines: List<String>, val poetic: Boolean = true) : Block

    /** Slot filled from today's lectionary (the Psalm or Gospel for the day). */
    data class DayReading(val kind: DayReadingKind) : Block

    /** A quiet attribution / footnote. */
    data class Attribution(val text: String) : Block
}

fun prose(vararg paragraphs: String) = Block.Prose(paragraphs.toList())
fun lines(vararg l: String) = Block.Lines(l.toList())
