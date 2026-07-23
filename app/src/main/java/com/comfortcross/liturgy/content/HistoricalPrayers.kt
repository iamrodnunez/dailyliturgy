package com.comfortcross.liturgy.content

/**
 * Prayers of the saints through the ages — the "historical" prayers in the daily-prayer
 * tradition. Every prayer here is an ancient or historic text whose author died long ago,
 * given in its traditional, public-domain English wording. These are the underlying
 * public-domain prayers themselves, not a reproduction of any copyrighted collection.
 */
data class HistoricalPrayer(
    val attribution: String,
    /** Lines of the prayer; each is rendered on its own line. */
    val lines: List<String>,
)

object HistoricalPrayers {

    val title = "Historical Prayers"
    val subtitle = "Prayers of the saints, through the ages"

    val all: List<HistoricalPrayer> = listOf(
        HistoricalPrayer(
            "St. Irenaeus, 130–202",
            listOf(
                "O Lord our God, who takes away the sin of the world,",
                "look upon us and have mercy upon us;",
                "you who are yourself both victim and priest,",
                "yourself both reward and redeemer,",
                "keep safe from all evil those whom you have redeemed,",
                "O Savior of the world. Amen.",
            ),
        ),
        HistoricalPrayer(
            "St. Ambrose of Milan, 339–397",
            listOf(
                "O Lord, who has mercy upon all,",
                "take away from me my sins,",
                "and mercifully kindle in me the fire of your Holy Spirit.",
                "Take away from me the heart of stone,",
                "and give me a heart of flesh,",
                "a heart to love and adore you,",
                "a heart to delight in you, to follow and enjoy you. Amen.",
            ),
        ),
        HistoricalPrayer(
            "St. Chrysostom, 347–407",
            listOf(
                "Almighty God, who has given us grace at this time",
                "with one accord to make our common supplications to you,",
                "and does promise that when two or three are gathered together",
                "in your Name you will grant their requests:",
                "Fulfill now, O Lord, the desires and petitions of your servants",
                "as may be best for them;",
                "granting us in this world knowledge of your truth,",
                "and in the world to come life everlasting. Amen.",
            ),
        ),
        HistoricalPrayer(
            "St. Augustine of Hippo, 354–430",
            listOf(
                "Breathe in me, O Holy Spirit, that my thoughts may all be holy.",
                "Act in me, O Holy Spirit, that my work, too, may be holy.",
                "Draw my heart, O Holy Spirit, that I love but what is holy.",
                "Strengthen me, O Holy Spirit, to defend all that is holy.",
                "Guard me, then, O Holy Spirit, that I always may be holy. Amen.",
            ),
        ),
        HistoricalPrayer(
            "St. Patrick, 387–493",
            listOf(
                "Christ with me, Christ before me, Christ behind me,",
                "Christ in me, Christ beneath me, Christ above me,",
                "Christ on my right, Christ on my left,",
                "Christ when I lie down, Christ when I sit down, Christ when I arise,",
                "Christ in the heart of everyone who thinks of me,",
                "Christ in the mouth of everyone who speaks of me,",
                "Christ in every eye that sees me,",
                "Christ in every ear that hears me. Amen.",
            ),
        ),
        HistoricalPrayer(
            "St. Benedict of Nursia, 480–547",
            listOf(
                "Gracious and holy Father,",
                "give us wisdom to perceive you,",
                "intelligence to understand you,",
                "diligence to seek you,",
                "patience to wait for you,",
                "eyes to behold you,",
                "a heart to meditate upon you,",
                "and a life to proclaim you;",
                "through the power of the Spirit of Jesus Christ our Lord. Amen.",
            ),
        ),
        HistoricalPrayer(
            "St. Columba, 521–597",
            listOf(
                "Alone with none but you, my God,",
                "I journey on my way.",
                "What need I fear, when you are near,",
                "O King of night and day?",
                "More safe am I within your hand",
                "than if a host did round me stand. Amen.",
            ),
        ),
        HistoricalPrayer(
            "Anima Christi, medieval",
            listOf(
                "Soul of Christ, sanctify me.",
                "Body of Christ, save me.",
                "Blood of Christ, inebriate me.",
                "Water from the side of Christ, wash me.",
                "Passion of Christ, strengthen me.",
                "O good Jesus, hear me.",
                "Within your wounds hide me.",
                "Never let me be parted from you.",
                "In the hour of my death call me,",
                "and bid me come to you,",
                "that with your saints I may praise you",
                "for ever and ever. Amen.",
            ),
        ),
        HistoricalPrayer(
            "St. Anselm of Canterbury, 1033–1109",
            listOf(
                "Lord, because you have made me, I owe you the whole of my love;",
                "because you have redeemed me, I owe you the whole of myself;",
                "because you have promised so much, I owe you my whole being.",
                "Draw me to you, Lord, in the fullness of your love.",
                "I am wholly yours by creation;",
                "make me all yours, too, in love. Amen.",
            ),
        ),
        HistoricalPrayer(
            "St. Francis of Assisi, 1181–1226",
            listOf(
                "Lord, make me an instrument of your peace.",
                "Where there is hatred, let me sow love;",
                "where there is injury, pardon;",
                "where there is doubt, faith;",
                "where there is despair, hope;",
                "where there is darkness, light;",
                "and where there is sadness, joy.",
                "O Divine Master, grant that I may not so much seek",
                "to be consoled as to console;",
                "to be understood as to understand;",
                "to be loved as to love.",
                "For it is in giving that we receive;",
                "it is in pardoning that we are pardoned;",
                "and it is in dying that we are born to eternal life. Amen.",
            ),
        ),
        HistoricalPrayer(
            "St. Thomas Aquinas, 1225–1274",
            listOf(
                "Grant me, O Lord my God,",
                "a mind to know you,",
                "a heart to seek you,",
                "wisdom to find you,",
                "conduct pleasing to you,",
                "faithful perseverance in waiting for you,",
                "and a hope of finally embracing you. Amen.",
            ),
        ),
        HistoricalPrayer(
            "Thomas à Kempis, 1380–1471",
            listOf(
                "Lord, you know what is best;",
                "let this be done or that be done as you please.",
                "Give what you will, as much as you will, when you will.",
                "Do with me as you know best,",
                "as will most please you, and be for your greater honor.",
                "Behold, I am your servant, ready to obey in all things. Amen.",
            ),
        ),
        HistoricalPrayer(
            "St. Ignatius Loyola, 1491–1556",
            listOf(
                "Take, Lord, and receive all my liberty,",
                "my memory, my understanding, and my entire will,",
                "all that I have and possess.",
                "You have given all to me; to you, O Lord, I return it.",
                "All is yours; dispose of it wholly according to your will.",
                "Give me your love and your grace,",
                "for this is enough for me. Amen.",
            ),
        ),
        HistoricalPrayer(
            "John Wesley, 1703–1791",
            listOf(
                "I am no longer my own, but yours.",
                "Put me to what you will, rank me with whom you will;",
                "put me to doing, put me to suffering;",
                "let me be employed for you or laid aside for you,",
                "exalted for you or brought low for you;",
                "let me be full, let me be empty;",
                "let me have all things, let me have nothing:",
                "I freely and wholeheartedly yield all things",
                "to your pleasure and disposal.",
                "And now, O glorious and blessed God,",
                "Father, Son, and Holy Spirit,",
                "you are mine and I am yours. So be it. Amen.",
            ),
        ),
        HistoricalPrayer(
            "A Celtic Blessing",
            listOf(
                "Deep peace of the running wave to you,",
                "deep peace of the flowing air to you,",
                "deep peace of the quiet earth to you,",
                "deep peace of the shining stars to you,",
                "deep peace of the Son of Peace to you. Amen.",
            ),
        ),
    )
}
