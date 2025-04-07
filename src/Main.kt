var name = ""

val progress = StoryProgress()
val console = Console()

object StoryFlags {
    const val BECAME_FRIENDS_WITH_CAT = "Friends with cat"
    const val CAGE_DOOR_IS_OPEN = "Cage door is open"
}

object Prison {
    private var lookCount = 0

    fun wakeUp() {
        console.print("Du bist in einem großen Vogelkäfig und hast Kopfschmerzen. Vor dem Käfig sitzt eine Stofftierkatze und schaut Dich neugierig an. Am Käfig hängt ein Zettel.")
        val choice = console.multipleChoice("Was machst Du?", progress.createOptions()
            .addOption("Der Katze winken").unless(StoryFlags.BECAME_FRIENDS_WITH_CAT)
            .addOption("Den Zettel lesen")
            .addOption("Dich weiter umschauen").buildMenu()
        )

        choice.on("Der Katze winken") {
            talkToCat()
        }.on("Den Zettel lesen") {
            pieceOfPaper()
        }.on("Dich weiter umschauen") {
            lookAround()
        }
    }

    fun talkToCat() {
        console.print("Die Katze kommt zu dir rüber und fragt: 'Wie heißt Du?'")
        val choice = console.multipleChoice("Was machst", listOf(
            "Deinen Namen sagen",
            "Hat das Stofftier gerade mit mir geredet? Ich muss ganz schön einen auf den Kopf bekommen haben ..."
        ))
        choice.on("Deinen Namen sagen") {
            name = console.askRaw("Wie heißt Du?").toString()
            console.print("Hallo $name! Ich heiße Lilli. Der Böse Zauberer hat uns hier eingeschlossen. Lass uns zusammen ausbrechen!")
        }.on("Hat das Stofftier gerade mit mir geredet? Ich muss ganz schön einen auf den Kopf bekommen haben ...") {
            console.print("Natürlich habe ich mit Dir geredet! Ich bin ein magisches Stofftier und hier mit dir eingesperrt. Mein Name ist Lilli")
            name = console.askRaw("Wie heißt Du?").toString()
            console.print("Hallo $name! Schön dich kennenzulernen. Der Böse Zauberer hat und eingesperrt. Jetzt lass uns ausbrechen!")
        }
        progress.remember(StoryFlags.BECAME_FRIENDS_WITH_CAT)
        wakeUp()
    }

    fun pieceOfPaper() {
        if (progress.didItHappen(StoryFlags.BECAME_FRIENDS_WITH_CAT)) {
            console.print("Lilli putzt sich und schaut hoch als Du ihr den Zettel zeigst. 'Da steht 1, 2, 4, 8, 16, ??'")

            val answer = console.ask("Was ist die nächste Zahl?")
            if (answer.matchesExactly("32")) {
                console.print("Du sagst die Antwort und die Käfigtür springt auf!")
                progress.remember(StoryFlags.CAGE_DOOR_IS_OPEN)
                Hallway.enterHallway()
            } else {
                pieceOfPaper()
            }

        } else {
            console.print("Da sind unleserliche Katzenzeichen auf dem Zettel. Du kannst nicht lesen was da steht.")
            wakeUp()
        }
    }

    fun lookAround() {
        if (lookCount == 0) {
            console.print("Der Raum ist weiß und etwas schmutzig. Sehr ungemütlich hier")
        } else if (lookCount == 1) {
            console.print("Hiar sind nur Du, die Katze und ihr seid im Käfig.")
        } else {
            console.print("Also viel Geld hat man hier nicht ausgegeben und der Innenarchitekt ist sicher ein sehr langweiliger Typ...")
        }
        console.print("")
        lookCount++
        wakeUp()
    }
}

object Hallway {
    fun enterHallway() {
        console.print("Du öffnest die Tür und ihr schleicht in den Flur")
        describeHallway()
    }

    fun describeHallway() {
        console.print("Am entfernten Ende des Flurs steht ein Troll vor einer Tür und guckt grimmig. Es gehen 2 Türen von dme Flur ab und es gibt eine Treppe nach unten.")
        val choice = console.multipleChoice("Was machst Du?", progress.createOptions()
            .addOption("...")
            .buildMenu())
        // ...
    }
}

fun main() {
   Prison.wakeUp()

}