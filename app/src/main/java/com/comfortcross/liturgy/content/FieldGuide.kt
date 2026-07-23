package com.comfortcross.liturgy.content

/**
 * Morning and Evening Prayer in the historic Daily Office tradition — the same shape a
 * field guide for daily prayer follows: opening, confession, invitatory, psalm, reading,
 * creed, the Lord's Prayer, prayers, a collect, and a benediction.
 *
 * Every fixed text here is a traditional, public-domain form — the Book of Common Prayer
 * confession, suffrages, and collects; the ancient canticle Phos Hilaron; the Apostles'
 * Creed and the Lord's Prayer (from [DevotionalTexts]); and King James Version scripture.
 * The psalm and reading are drawn from today's appointed daily-lectionary readings. This
 * is not a reproduction of any copyrighted guide.
 */
object FieldGuide {

    private val gloria = Block.Lines(
        listOf(
            "Glory be to the Father, and to the Son, and to the Holy Ghost;",
            "as it was in the beginning, is now, and ever shall be,",
            "world without end. Amen.",
        )
    )

    private val confession = Block.Lines(
        listOf(
            "Most merciful God,",
            "we confess that we have sinned against thee",
            "in thought, word, and deed,",
            "by what we have done,",
            "and by what we have left undone.",
            "We have not loved thee with our whole heart;",
            "we have not loved our neighbors as ourselves.",
            "We are truly sorry and we humbly repent.",
            "For the sake of thy Son Jesus Christ,",
            "have mercy on us and forgive us;",
            "that we may delight in thy will,",
            "and walk in thy ways,",
            "to the glory of thy Name. Amen.",
        )
    )

    private val absolution = Block.Lines(
        listOf(
            "The Almighty and merciful Lord",
            "grant us pardon and remission of all our sins,",
            "true repentance, amendment of life,",
            "and the grace and consolation of his Holy Spirit. Amen.",
        )
    )

    private val attribution = Block.Attribution(
        "An order in the historic Daily Office tradition. The confession, suffrages, and " +
            "collects are in their traditional public-domain wording; the canticle Phos Hilaron " +
            "is an ancient hymn; scripture sentences are from the public-domain King James " +
            "Version. The psalm and reading are today's appointed daily-lectionary readings.",
    )

    val morningBlocks: List<Block> = buildList {
        add(Block.Section("The Opening"))
        add(Block.Response("O Lord, open thou my lips;", "and my mouth shall show forth thy praise."))
        add(gloria)

        add(Block.Section("Confession of Sin", "A short silence is kept."))
        add(confession)
        add(absolution)

        add(Block.Section("The Psalm", "The psalm appointed for today."))
        add(Block.DayReading(DayReadingKind.PSALM))

        add(Block.Section("The Reading", "The reading appointed for today."))
        add(Block.DayReading(DayReadingKind.GOSPEL))
        add(Block.Rubric("Read slowly. Where a word or phrase stays with you, stay there with it."))

        add(Block.Recite("The Apostles’ Creed", DevotionalTexts.apostlesCreed, poetic = false))
        add(Block.Recite("The Lord’s Prayer", DevotionalTexts.lordsPrayer, poetic = false))

        add(Block.Section("The Suffrages"))
        add(Block.Response("Show us thy mercy, O Lord;", "and grant us thy salvation."))
        add(Block.Response("Give peace in our time, O Lord;", "for only in thee can we live in safety."))
        add(Block.Response("Create in us clean hearts, O God;", "and sustain us with thy Holy Spirit."))
        add(Block.Rubric("Pray for the church and the world, for those you love, and for the day now before you."))

        add(Block.Section("A Collect for Grace"))
        add(
            Block.Prose(
                listOf(
                    "O Lord, our heavenly Father, Almighty and everlasting God, who hast safely " +
                        "brought us to the beginning of this day: Defend us in the same with thy " +
                        "mighty power; and grant that this day we fall into no sin, neither run " +
                        "into any kind of danger; but that all our doings, being ordered by thy " +
                        "governance, may be righteous in thy sight; through Jesus Christ our Lord. Amen.",
                )
            )
        )

        add(Block.Section("Benediction"))
        add(
            Block.Lines(
                listOf(
                    "The grace of our Lord Jesus Christ, and the love of God,",
                    "and the fellowship of the Holy Ghost, be with us all evermore.",
                    "Amen. (2 Corinthians 13:14)",
                )
            )
        )
        add(attribution)
    }

    val eveningBlocks: List<Block> = buildList {
        add(Block.Section("The Opening"))
        add(Block.Response("O God, make speed to save us.", "O Lord, make haste to help us."))
        add(gloria)

        add(Block.Section("Confession of Sin", "A short silence is kept."))
        add(confession)
        add(absolution)

        add(Block.Section("Phos Hilaron", "O Gracious Light — an ancient evening hymn."))
        add(
            Block.Lines(
                listOf(
                    "O gladsome Light, pure brightness of the everliving Father in heaven,",
                    "O Jesus Christ, holy and blessed!",
                    "Now as we come to the setting of the sun,",
                    "and our eyes behold the vesper light,",
                    "we sing thy praises, O God: Father, Son, and Holy Spirit.",
                    "Thou art worthy at all times to be praised by happy voices,",
                    "O Son of God, O Giver of life,",
                    "and to be glorified through all the worlds.",
                )
            )
        )

        add(Block.Section("The Psalm", "The psalm appointed for today."))
        add(Block.DayReading(DayReadingKind.PSALM))

        add(Block.Section("The Reading", "The reading appointed for today."))
        add(Block.DayReading(DayReadingKind.GOSPEL))
        add(Block.Rubric("Keep a short silence. Ask: what is God saying to me, and what will I do about it?"))

        add(Block.Recite("The Apostles’ Creed", DevotionalTexts.apostlesCreed, poetic = false))
        add(Block.Recite("The Lord’s Prayer", DevotionalTexts.lordsPrayer, poetic = false))

        add(Block.Section("A Collect for the Presence of Christ"))
        add(
            Block.Prose(
                listOf(
                    "Lord Jesus, stay with us, for evening is at hand and the day is past; be our " +
                        "companion in the way, kindle our hearts, and awaken hope, that we may " +
                        "know thee as thou art revealed in Scripture and the breaking of bread. " +
                        "Grant this for the sake of thy love. Amen.",
                )
            )
        )

        add(Block.Section("Benediction"))
        add(
            Block.Lines(
                listOf(
                    "Now the God of hope fill you with all joy and peace in believing,",
                    "that ye may abound in hope, through the power of the Holy Ghost.",
                    "Amen. (Romans 15:13)",
                )
            )
        )
        add(attribution)
    }
}
