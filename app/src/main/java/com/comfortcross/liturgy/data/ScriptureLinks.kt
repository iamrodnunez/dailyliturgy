package com.comfortcross.liturgy.data

import android.net.Uri

/** A single tappable passage extracted from a lectionary citation string. */
data class PassageLink(val reference: String, val url: String)

/**
 * Turns a Vanderbilt citation (which may bundle a reading and psalm, and offer
 * "or" alternatives) into individual passages that open in the NRSVue on Bible Gateway.
 *
 * e.g. "Genesis 29:15-28 and Psalm 105:1-11, 45b or Psalm 128"
 *   -> [Genesis 29:15-28, Psalm 105:1-11, 45b, Psalm 128]
 */
object ScriptureLinks {

    private const val VERSION = "NRSVUE"

    fun forCitation(citation: String): List<PassageLink> =
        splitPassages(citation).map { ref ->
            PassageLink(ref, bibleGatewayUrl(ref))
        }

    private fun splitPassages(citation: String): List<String> =
        citation
            .split(" and ", " or ", " AND ", " OR ")
            .map { it.trim() }
            .filter { it.isNotEmpty() && it.any(Char::isDigit) }
            .distinct()

    fun bibleGatewayUrl(reference: String): String {
        val q = Uri.encode(reference)
        return "https://www.biblegateway.com/passage/?search=$q&version=$VERSION"
    }

    const val VANDERBILT_URL = "https://lectionary.library.vanderbilt.edu/"
    const val DAILY_READINGS_URL = "https://www.dailylectio.net/"
}
