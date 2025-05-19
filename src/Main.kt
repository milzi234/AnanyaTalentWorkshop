var name = ""
val progress = StoryProgress()
val console = Console()
object StoryFlags {
    const val FOUND_STAIRS = "Found stairs"
    const val BECAME_FRIENDS_WITH_CAT = "Friends with cat"
    const val CAGE_DOOR_IS_OPEN = "Cage door is open"
    const val BECAME_FRIENDS_WITH_TROLL="Friends with Troll"
    const val OPENED_THE_BAG="The bag is open"
    const val DOOR_ONE_DONE="The Door 1 is done"
    const val STAIRCASE_DONE="The Staircase is done"
    const val ANSWERED_THE_QUESTION="The question is answered"
    const val FRIEND_WITH_ZWE="Friends with Zwe"
    const val MET_GIANT = "Met giant"
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
        val choice = console.multipleChoice("Was machst du", listOf(
            "Deinen Namen sagen",
            "Hat das Stofftier gerade mit mir geredet? Ich muss ganz schön einen auf den Kopf bekommen haben ..."
        ))
        choice.on("Deinen Namen sagen") {
            name = console.askRaw("Wie heißt Du?").toString()
            console.print("Hallo $name! Ich heiße Lilli. Der Böse Zauberer hat uns hier eingeschlossen. Lass uns zusammen ausbrechen!")
        }.on("Hat das Stofftier gerade mit mir geredet? Ich muss ganz schön einen auf den Kopf bekommen haben ...") {
            console.print("Natürlich habe ich mit Dir geredet! Ich bin ein magisches Stofftier und hier mit dir eingesperrt. Mein Name ist Lilli")
            name = console.askRaw("Wie heißt Du?").toString()
            console.print("Hallo $name! Schön dich kennenzulernen.Ich heiße Lilli. Der Böse Zauberer hat und eingesperrt. Jetzt lass uns ausbrechen!")
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
        console.print(
            " " +
                    "Am entfernten Ende des Flurs steht ein Troll vor einer Tür und guckt grimmig. Es gehen 2 Türen von dem Flur ab und es gibt eine Treppe nach unten."
        )
        val choice = console.multipleChoice(
            "Was machst Du?", progress.createOptions()
                .addOption("Den Troll fragen warum er so grimmig guckt")
                .unless(StoryFlags.BECAME_FRIENDS_WITH_TROLL)
                .addOption("Zur Richtung von den zwei Turen und der Treppe gehen")
                .addOption("Zurück zum Raum mit dem Käfig gehen").buildMenu()
        )

        fun askTheTroll() {
            console.print("Der Troll sagt'Ach ich hätte so gerne einen Keks.Aber der Koch will mir keinen Keks geben. Hilfst du mir.'")
            val choice = console.multipleChoice(
                "Was machst Du?", listOf(
                    "Ihn helfen",
                    "Ihn nicht helfen"
                )
            )
            fun helpTroll(){
                console.print(
                    "Er zeigt dir den Weg zur Küche.Dort fragst du den Koch ob er dir einen Keks gibt.Er sagt:'Na gut aber nur wenn du diese Aufgabe löst." +
                            "An einem Hafen kommt ein Boot entweder nach 2,4,8,12 oder 16 Wochen.Nach wie vielen Wochen treffen sich alle Schiffe wieder am Hafen'"
                )
                val answer = console.ask("Was ist die Antwort?")
                if (answer.matchesExactly("48")) {
                    console.print("Du sagst die Antwort und der Koch gibt dir den Keks!Den Keks gibst du den Troll der aufgergt auf dich wartet")
                } else {
                    console.print("Die Antwort ist falsch.Versuche es nochmal.")
                    helpTroll()
                }
            }
            fun donHelpTroll(){
                console.print(
                    "Der Troll wird dann sehr traurig.Du wolltest den Troll nicht traurig machen also hilfst du ihn doch.Der Troll wird sehr fröhlich ." +
                            "\n Er zeigt dir den Weg zur Küche.Dort fragst du den Koch ob er dir einen Keks gibt.Er sagt:'Na gut aber nur wenn du diese Aufgabe löst." +
                            "\nAn einem Hafen kommt ein Boot entweder nach 2,4,8,12 oder 16 Wochen.Nach wie vielen Wochen treffen sich alle Schiffe wieder am Hafen'"
                )
                val answer = console.ask("Was ist die Antwort?")
                if (answer.matchesExactly("48")) {
                    console.print("Du sagst die Antwort und der Koch gibt dir den Keks!Den Keks gibst du den Troll der aufgergt auf dich wartet")
                } else {
                    console.print("Die Antwort ist falsch.Versuche es nochmal.")
                    donHelpTroll()
                }
            }
            choice.on("Ihn helfen") {
                helpTroll()
            }.on("Ihn nicht helfen") {

            }
            progress.remember(StoryFlags.BECAME_FRIENDS_WITH_TROLL)
            describeHallway()
        }
        choice.on("Den Troll fragen warum er so grimmig guckt") {
            askTheTroll()
        }.on("Zur Richtung von den zwei Turen und der Treppe gehen") {
            doorAndStairs()
        }.on("Zurück zum Raum mit dem Käfig gehen") {
            backToPrison()
        }

    }

    fun backToPrison() {
        console.print("Du und die Katze sitzt wieder in diesen furchtbar langweiligen Raum.Viel tun kann man nicht")
        describeHallway()
    }
    fun doorAndStairs() {
        if (progress.didItHappen(StoryFlags.BECAME_FRIENDS_WITH_TROLL)) {
            val choice = console.multipleChoice(
                "Wohin gehst du", listOf(
                    "Zum Treppenhaus",
                    "Zur 1.Tür",
                    "Zur 2.Tür"
                )
            )
            choice.on("Zum Treppenhaus") {
                stairWay()
            }.on("Zur 1.Tür") {
                door1()
            }.on("Zur 2.Tür") {
                door2()
            }
        } else {
            console.print("Der Troll lässt dich nicht durch")
            describeHallway()
        }
    }
    private fun stairWay() {
        console.print(
            "Du gehst in der Richtung des Treppenhaus . Der Troll sagt bevor ihr geht nimmt noch diesen Rucksack.Das ist sozusagen mein dankeschön.Du nimmst den Rucksack und du und die Katze geht weiter" +
                    "\nEs ist sehr dunkel dort und man kann nichts sehen . Du findest eine Taschenlampe und benutzt sie. Unten gibt es ein Raum .Ihr geht in den Raum rein .Plötzlich schließt die Tür sich selber" +
                    "\nund jetzt wenn es erscheint das es nicht schlimmer werden kann ,ist die Batterieder Taschenlampe leer."
        )
        val choice = console.multipleChoice(
            "Was machst du?", listOf(
                "Gucken was in den Rucksack ist",
                "Versuchen die Tür aufzumachen",
                "Du schaust dich um"
            )
        )
        choice.on("Gucken was in den Rucksack ist") {
            backPack()
        }.on("Versuchen die Tür aufzumachen") {
            openDoor()
        }.on("Du schaust dich um") {
            breakTime()
        }
    }
    fun backPack() {
        console.print(
            "Du machst den Rucksack auf und fühlst eine Batterie .Du tust es in die Taschenlampe rein und die Taschenlampe leuchtet.Juhu!!!" +
                    "Im Rucksack ist noch ein halb aufgegessen Sandwich und eine Wasserflasche."
        )
        progress.remember(StoryFlags.OPENED_THE_BAG)
        stairWay()
    }
    fun openDoor() {
        console.print("Du versuchst die Tür aufzumachen. Aber egal wie hart du ziehst und drückst geht die Tür nicht auf.Du gibst nach einer Weile auf ,da deine Hände wehtun.")
        stairWay()
    }
    fun breakTime() {
        if (progress.didItHappen(StoryFlags.OPENED_THE_BAG)) {
            console.print("Du schaust dich um auf den Boden findest du ein Zettel aber nur eine Hälfte von einem Zettel.Du steckst den Zettel in deiner Hosentasche . An der Tür ist ein Bildschirm ." +
                    "\nAuf den Bildschirm steht:'Eine kaputte Uhr läuft doppelt so schnell wie eine normale Uhr. Wenn die Uhr um 7:45 angeschaltet wird welche Uhrzeit zeigt es um 14:45'")
            val answer = console.ask("Was ist die Antwort?")
            if (answer.matchesExactly("21:45")) {
                console.print("Du sagst die Antwort und die Tür öffnet sich")

            } else {
                console.print("Die Antwort ist falsch.Versuche es nochmal.")
                breakTime()
            }
        } else {

            console.print("Man kann nichts außer schwarz sehen.Sogar mit 100 Glühwürmchen sieht man nichts.Es ist komplett dunkel")
        }
        progress.remember(StoryFlags.STAIRCASE_DONE)
        doorAndStairs()
    }
}
fun door1(){
    console.print("Du gehst zur Tür und machst Sie auf . Es is ganz leer im Raum außer für den Schrank. Du gehst zum Schrank. Du ziehst an der Tür, aber sie geht nicht auf." +
            "\nAm Schrank klebt ein Zettel dadrauf steht :'In meiner Schublade sind 10 rote und 10 gelbe Socken wie viele muss ich mindestens mit meinen Augen zu ziehen damit ich immer von einer Farbe mindestens 2 habe'")
    val answer = console.ask("Was ist die Antwort?")
    if (answer.matchesExactly("3")) {
        console.print("Du sagst die Antwort und der Schrank öffnet sich.Da drinne ist ein halber Zettel")
    } else {
        console.print("Die Antwort ist falsch.Versuche es nochmal.")
        door1()

    }
    Hallway.doorAndStairs()
    progress.remember(StoryFlags.DOOR_ONE_DONE)
}
fun door2(){
    console.print("Du gehst in den Raum rein.Hinten im Raum ist eine Zwerg . Er hat ein Schlüssel in der Hand . Er sagt nur ein Wort :'Passwort!Passwort!Passwort!...' ")
    if(progress.didItHappen(StoryFlags.STAIRCASE_DONE)&&progress.didItHappen(StoryFlags.DOOR_ONE_DONE)){
        val choice = console.multipleChoice(
            "Was machst du?", listOf(
                "Du bietest dein Sandwich dem Zwerg an",
                "Versuchen mit dem Zwerg zu sprechen",
                "Die zwei Zettel anschauen die du gefunden hast"
            )
        )
        choice.on("Du bietest dein Sandwich dem Zwerg an") {
            giveSandwich()
        }.on("Versuchen mit dem Zwerg zu sprechen") {
            talkToZwi()
        }.on("Die zwei Zettel anschauen die du gefunden hast") {
            readTwoPapers()
        }
    }
    else{
        console.print("Du hast keine Ahnung was der Zwerg meint. Vielleicht gehst du irgendwo anders hin bevor der Zwerg komplett ausflippt.")
    }
}
fun giveSandwich(){
    if (progress.didItHappen(StoryFlags.ANSWERED_THE_QUESTION)){
        console.print("Der Zwerg nimmt dein Sandwich gerne an.Er isst es glücklich auf.")
        progress.remember(StoryFlags.FRIEND_WITH_ZWE)
    }else{
        console.print("Er sagt nur:'Passwort!Passwort!Passwort!...'")
    }
}
fun talkToZwi(){
    if(progress.didItHappen(StoryFlags.ANSWERED_THE_QUESTION)&&progress.didItHappen(StoryFlags.FRIEND_WITH_ZWE)){
        console.print("Er sagt:'Der böse Zauberer hat mich unter einen Fluch gesetzt , aber jetzt hast du mich befreit.Ich dachte ich würde für immer so bleiben. Hier ich lasse dich durch die Tür.'" +
                "\nDu bedankst dich beim Zwerg und gehst weiter.")
        hallWay2()
    }else if(progress.didItHappen(StoryFlags.ANSWERED_THE_QUESTION)){
        console.print("Er guckt dich an.Er sagt nichts.Aber hat aufgehört 'Passwort' zu sagen")
    }else {
        console.print("Er sagt nur:'Passwort!Passwort!Passwort!...'")
    }
}
fun readTwoPapers(){
    console.print("Du tust die beiden Hälften zusammen und du erkennst einen Text ." +
            "\n Der lautet:'Ein Riesenrad hat 28 Gondeln.In einer Gondel können maximal 7 Leute mitfahren.Wie viele Leute können maximal mitfahren wenn 4 Gondeln kaputt sind'")
    val answer = console.ask("Was ist die Antwort?")
    if (answer.matchesExactly("168")) {
        console.print("Du sagst die Antwort und der Zwerg sagt 'richtig' aber mehr nicht")
    } else {
        console.print("Die Antwort ist falsch.Versuche es nochmal.")
        readTwoPapers()
    }
    progress.remember(StoryFlags.ANSWERED_THE_QUESTION)
}
fun hallWay2(){
    console.print("Du gehst rein und du siehst 3 Türen und ein Weg zum Dachboden. Eine Tür ist sogar mit einem Schloss fest verschlossen")
    val choice = console.multipleChoice(
        "Wohin gehst du?", listOf(
            "Zur 1.Tür",
            "Zur 2.Tür",
            "Zum Dachboden"
        )
    )
    choice.on("Zur 1.Tür"){
        RoomWithGiant.door3()
    }.on("Zur 2.Tür") {
        door4()
    }.on("Zum Dachboden"){
        Way1()
    }
}


object RoomWithGiant {
    fun door3(){
        console.print("Du gehst rein und du siehst einen riesigen Schuh! Er sagt: \"Hallo, komm' hoch zu mir, damit wir uns unterhalten können.\"")
        choose()
    }

    private fun choose() {
        val choice = console.multipleChoice(
            "Was tust Du?", progress.createOptions()
                .addOption("Den Schuh fragen, wo er drückt").unless(StoryFlags.MET_GIANT)
                .addOption("Dich umschauen")
                .addOption("Die Treppe hoch gehen").inCase(StoryFlags.FOUND_STAIRS)
                .buildMenu()
        )
        choice.on("Den Schuh fragen, wo er drückt"){
            talkToShoe()
        }.on("Dich umschauen"){
            lookAround()
        }.on("Die Treppe hoch gehen"){
            goToGiantsHead()
        }
    }

    fun room3() {
        if (progress.didItHappen(StoryFlags.FOUND_STAIRS)) {
            console.print("Da ist immer noch ein riesiger Schuh und eine Treppe nach oben")
        } else {
            console.print("Da ist immer noch ein riesiger Schuh.")

        }
        choose()
    }

    fun talkToShoe(){
        console.print("Der Schuh sagt: \"Hey! Ich bin doch kein Schuh! Ich bin ein Riese! Schau' mal nach oben.\"")
        val choice = console.multipleChoice(prompt = "Was machst Du?", listOf(
            "Nach oben gucken",
            "Den Schuh überzeugen, dass er ein Schuh ist"
        ))

        choice.on("Den Schuh überzeugen, dass er ein Schuh ist") {
            console.print("Aber Mr. Schuh! Ich sehe eindeutig, dass sie ein Schuh sind! Solche Schuhe wie Sie gibt es auf jeden Fall nicht bei Deichmann... Sie müssen ein Schuh sein!")
            console.print("Der Schuch lacht ... \"Den Schuch habe ich vom bösen Zauberer bekommen, damit hier nicht alles nach Käsefuß stinkt.\". Du schaust nach oben.")
        }
        console.print("Der Raum ist mindestens 100m hoch und gaaanz oben ahnst Du eine Nase! Das ist ein Riese! Aber er scheint ja nett zu sein")
        progress.remember(StoryFlags.MET_GIANT)
        room3()
    }

    fun lookAround(){
        console.print("Da ist noch ein Schrank in den Zimmer.")
        progress.remember(StoryFlags.FOUND_STAIRS)
        room3()
    }

    fun goToGiantsHead(){

    }

}


fun door4(){
    console.print("abc")
}
fun Way1(){
    console.print("abc")
}
fun main() {
    //Prison.wakeUp()
    RoomWithGiant.door3()
}