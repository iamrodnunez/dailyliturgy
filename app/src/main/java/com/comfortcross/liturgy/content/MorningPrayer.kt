package com.comfortcross.liturgy.content

/**
 * A Liturgy for Morning Prayer, after the Rev. Brian Zahnd.
 *
 * The framework and several of the prayers are Brian Zahnd's, offered freely for use.
 * Where his outline names a text to be prayed (the Creed, the Lord's Prayer, the psalms,
 * the Beatitudes), the historic public-domain wording is supplied here from
 * [DevotionalTexts] so the whole office can be prayed offline.
 */
object MorningPrayer {

    val title = "A Liturgy for Morning Prayer"
    val subtitle = "after Brian Zahnd"

    val blocks: List<Block> = buildList {
        add(Block.Section("Address"))
        add(
            Block.Lines(
                listOf(
                    "Father God, creator of heaven and earth,",
                    "God of Abraham, Isaac, and Jacob,",
                    "God of Israel,",
                    "God and Father of our Lord and Savior Jesus Christ,",
                    "True and Living God who is Father, Son, and Holy Spirit,",
                    "Have mercy and hear our prayer.",
                )
            )
        )

        add(Block.Section("First Prayers"))
        add(Block.Response("O Lord, open our lips.", "And our mouth shall proclaim your praise."))
        add(
            Block.Lines(
                listOf(
                    "Glory to the Father, and to the Son, and to the Holy Spirit:",
                    "as it was in the beginning, is now, and will be forever. Amen.",
                )
            )
        )
        add(Block.Response("O God, make speed to save us.", "O Lord, make haste to help us."))
        add(
            Block.Lines(
                listOf(
                    "Glory to the Father, and to the Son, and to the Holy Spirit:",
                    "as it was in the beginning, is now, and will be forever. Amen.",
                )
            )
        )

        add(Block.Section("The Jesus Prayer"))
        add(Block.Rubric("Prayed slowly, as many times as you wish."))
        add(Block.Lines(listOf("Lord Jesus Christ, Son of God, have mercy on me.")))

        add(Block.Section("Confession of Sin"))
        add(
            Block.Lines(
                listOf(
                    "Most merciful God,",
                    "we confess that we have sinned against you",
                    "in thought, word, and deed,",
                    "by what we have done,",
                    "and by what we have left undone.",
                    "We have not loved you with our whole heart;",
                    "we have not loved our neighbors as ourselves.",
                    "We are truly sorry and we humbly repent.",
                    "For the sake of your Son Jesus Christ,",
                    "have mercy on us and forgive us;",
                    "that we may delight in your will,",
                    "and walk in your ways,",
                    "to the glory of your Name. Amen.",
                )
            )
        )

        add(Block.Section("Psalm for the Day"))
        add(Block.DayReading(DayReadingKind.PSALM))

        add(Block.Section("Gospel Reading"))
        add(Block.DayReading(DayReadingKind.GOSPEL))

        add(Block.Recite("The Apostles’ Creed", DevotionalTexts.apostlesCreed, poetic = false))

        add(Block.Section("The Jesus Prayer"))
        add(Block.Lines(listOf("Lord Jesus Christ, Son of God, have mercy on me.")))

        add(Block.Recite("Psalm 23", DevotionalTexts.psalm23))
        add(Block.Recite("Psalm 91:1–2", DevotionalTexts.psalm91_1_2))

        add(Block.Section("Prayer for Family"))
        add(Block.Rubric("Name before God those you love, and pray for them."))

        add(Block.Recite("Psalm 103:1–5", DevotionalTexts.psalm103_1_5))

        add(Block.Recite("The Lord’s Prayer", DevotionalTexts.lordsPrayer, poetic = false))

        add(Block.Section("The Jesus Prayer"))
        add(Block.Lines(listOf("Lord Jesus Christ, Son of God, have mercy on me.")))

        add(Block.Section("The Lord’s Prayer, Expanded"))
        add(
            Block.Rubric(
                "Pray the Lord's Prayer again, slowly, pausing at each petition to pray " +
                    "it in your own words and for your own day."
            )
        )

        add(Block.Section("Petition and Intercession"))
        add(
            Block.Rubric(
                "Bring your own requests to God, and intercede for others — the church, " +
                    "the world, the suffering, and those who have asked for prayer."
            )
        )

        add(Block.Section("Contemplation"))
        add(Block.Rubric("Be still, and rest in the presence of God."))

        add(Block.Section("Prayer to the Crucified Christ"))
        add(
            Block.Prose(
                listOf(
                    "Lord Jesus, you stretched out your arms of love upon the hard wood of the " +
                        "cross that everyone might come within the reach of your saving embrace: " +
                        "So clothe us in your Spirit that we, reaching forth our hands in love, " +
                        "may bring those who do not know you to the knowledge and love of you; " +
                        "for the honor of your name. Amen.",
                )
            )
        )

        add(Block.Recite("The Beatitudes", DevotionalTexts.beatitudes, poetic = false))

        add(Block.Section("Prayer for Peace"))
        add(
            Block.Prose(
                listOf(
                    "O God, you have made of one blood all the peoples of the earth, and sent " +
                        "your blessed Son to preach peace to those who are far off and to those " +
                        "who are near: Grant that people everywhere may seek after you and find " +
                        "you; bring the nations into your fold; pour out your Spirit upon all " +
                        "flesh; and hasten the coming of your kingdom; through Jesus Christ our " +
                        "Lord. Amen.",
                )
            )
        )

        add(Block.Section("Prayer for the Week"))
        add(Block.Rubric("Pray the collect appointed for this week, or a prayer of your own."))

        add(Block.Section("Prayer for Grace"))
        add(
            Block.Prose(
                listOf(
                    "Lord God, almighty and everlasting Father, you have brought us in safety to " +
                        "this new day: Preserve us with your mighty power, that we may not fall " +
                        "into sin, nor be overcome by adversity; and in all we do, direct us to " +
                        "the fulfilling of your purpose; through Jesus Christ our Lord. Amen.",
                )
            )
        )

        add(Block.Section("Prayer of Thanksgiving"))
        add(
            Block.Lines(
                listOf(
                    "Almighty God, Father of all mercies,",
                    "we your unworthy servants give you humble thanks",
                    "for all your goodness and lovingkindness",
                    "to us and to all whom you have made.",
                    "We bless you for our creation, preservation,",
                    "and all the blessings of this life;",
                    "but above all for your immeasurable love",
                    "in the redemption of the world by our Lord Jesus Christ;",
                    "for the means of grace, and for the hope of glory.",
                    "And, we pray, give us such an awareness of your mercies,",
                    "that with truly thankful hearts we may show forth your praise,",
                    "not only with our lips, but in our lives,",
                    "by giving up our selves to your service,",
                    "and by walking before you",
                    "in holiness and righteousness all our days;",
                    "through Jesus Christ our Lord,",
                    "to whom, with you and the Holy Spirit,",
                    "be honor and glory throughout all ages. Amen.",
                )
            )
        )

        add(Block.Section("Kyrie"))
        add(
            Block.Lines(
                listOf(
                    "Lord, have mercy.",
                    "Christ, have mercy.",
                    "Lord, have mercy.",
                )
            )
        )

        add(Block.Section("Confession of the Mystery"))
        add(
            Block.Lines(
                listOf(
                    "Christ has died.",
                    "Christ is risen.",
                    "Christ will come again.",
                )
            )
        )

        add(Block.Section("The Jesus Prayer"))
        add(Block.Lines(listOf("Lord Jesus Christ, Son of God, have mercy on me.")))

        add(
            Block.Attribution(
                "Framework and prayers after the Rev. Brian Zahnd’s “A Liturgy for Morning " +
                    "Prayer,” offered freely for use. Recited texts are in the traditional " +
                    "public-domain wording."
            )
        )
    }
}
