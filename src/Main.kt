import com.github.mm.coloredconsole.*
import kotlin.random.Random
import kotlin.system.exitProcess
import BossFight

var name = ""
val progress = StoryProgress()
val console = Console()

object StoryFlags {
  const val FOUND_STAIRS = "Found stairs"
  const val BECAME_FRIENDS_WITH_CAT = "Friends with cat"
  const val CAGE_DOOR_IS_OPEN = "Cage door is open"
  const val BECAME_FRIENDS_WITH_TROLL = "Friends with Troll"
  const val OPENED_THE_BAG = "The bag is open"
  const val DOOR_ONE_DONE = "The Door 1 is done"
  const val STAIRCASE_DONE = "The Staircase is done"
  const val ANSWERED_THE_QUESTION = "The question is answered"
  const val FRIEND_WITH_ZWE = "Friends with Zwe"
  const val MET_GIANT = "Met giant"
  const val MET_ELF = "Met elf"
  const val MET_UNICORN = "Met Unicorn"
}

object Prison : ColoredConsole {
  private var lookCount = 0
  fun wakeUp() {
    console.print(
            "Du bist in einem großen Vogelkäfig und hast Kopfschmerzen. Vor dem Käfig sitzt eine Stofftierkatze und schaut Dich neugierig an. Am Käfig hängt ein Zettel."
    )
    val choice =
            console.multipleChoice(
                    "Was machst Du?",
                    progress.createOptions()
                            .addOption("Der Katze winken")
                            .unless(StoryFlags.BECAME_FRIENDS_WITH_CAT)
                            .addOption("Den Zettel lesen")
                            .addOption("Dich weiter umschauen")
                            .buildMenu()
            )
    choice.on("Der Katze winken") { talkToCat() }.on("Den Zettel lesen") { pieceOfPaper() }.on(
                    "Dich weiter umschauen"
            ) { lookAround() }
  }
  fun talkToCat() {
    console.print("Die Katze kommt zu dir rüber und fragt: 'Wie heißt Du?'")
    val choice =
            console.multipleChoice(
                    "Was machst du",
                    listOf(
                            "Deinen Namen sagen",
                            "Hat das Stofftier gerade mit mir geredet? Ich muss ganz schön einen auf den Kopf bekommen haben ..."
                    )
            )
    choice
            .on("Deinen Namen sagen") {
              name = console.askRaw("Wie heißt Du?").toString()
              console.print(
                      "Hallo $name! Ich heiße Lilli. Der Böse Zauberer hat uns hier eingeschlossen. Lass uns zusammen ausbrechen!"
              )
            }
            .on(
                    "Hat das Stofftier gerade mit mir geredet? Ich muss ganz schön einen auf den Kopf bekommen haben ..."
            ) {
              console.print(
                      "Natürlich habe ich mit Dir geredet! Ich bin ein magisches Stofftier und hier mit dir eingesperrt. Mein Name ist Lilli"
              )
              name = console.askRaw("Wie heißt Du?").toString()
              console.print(
                      "Hallo $name! Schön dich kennenzulernen.Ich heiße Lilli. Der Böse Zauberer hat und eingesperrt. Jetzt lass uns ausbrechen!"
              )
            }
    progress.remember(StoryFlags.BECAME_FRIENDS_WITH_CAT)
    wakeUp()
  }

  var pieceOfPaperErrorCount: Int = 0 // Outside of the function, so that is doesn't reset when the function is called
  fun pieceOfPaper() {
    if (progress.didItHappen(StoryFlags.BECAME_FRIENDS_WITH_CAT)) {
      console.print(
              "Lilli putzt sich und schaut hoch als Du ihr den Zettel zeigst. 'Da steht 1, 2, 4, 8, 16, ??'"
      )

      if (pieceOfPaperErrorCount >= 3 && pieceOfPaperErrorCount <= 4) { // 3 and 4 wrong guesses
        console.print("")
        console.print("")
        console.print("Lili sagt: " + "Ich glaube die Zahlen verdoppeln sich immer. ".purple + "2".purple.bright + " ist das Doppelte von ".purple + "1".purple.bright + " und ".purple + "4".purple.bright + " dann das Doppelte von ".purple + "2".purple.bright + " und so weiter.".purple)
      } else if (pieceOfPaperErrorCount >= 5) { // all other wrong guesses
        console.print("")
        console.print("")
        console.print("Lili sagt: " + "Probier' mal ".purple + "32".purple.bright + ", das ist das Doppelte von ".purple + "16".purple.bright)
      }

      val answer = console.ask("Was ist die nächste Zahl?")
      if (answer.matchesExactly("32")) {
        console.print("Du sagst die Antwort und die Käfigtür springt auf!")
        progress.remember(StoryFlags.CAGE_DOOR_IS_OPEN)
        Hallway.enterHallway()
      } else {
        console.print("Die Tür bleibt verschlossen. Vermutlich nicht die richtige Antwort.")
        pieceOfPaperErrorCount++
        pieceOfPaper()
      }
    } else {
      console.print(
              "Da sind unleserliche Katzenzeichen auf dem Zettel. Du kannst nicht lesen was da steht."
      )
      wakeUp()
    }
  }
  fun lookAround() {
    if (lookCount == 0) {
      console.print("Der Raum ist weiß und etwas schmutzig. Sehr ungemütlich hier")
    } else if (lookCount == 1) {
      console.print("Hiar sind nur Du, die Katze und ihr seid im Käfig.")
    } else {
      console.print(
              "Also viel Geld hat man hier nicht ausgegeben und der Innenarchitekt ist sicher ein sehr langweiliger Typ..."
      )
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
    val choice =
            console.multipleChoice(
                    "Was machst Du?",
                    progress.createOptions()
                            .addOption("Den Troll fragen warum er so grimmig guckt")
                            .unless(StoryFlags.BECAME_FRIENDS_WITH_TROLL)
                            .addOption("Zur Richtung von den zwei Turen und der Treppe gehen")
                            .addOption("Zurück zum Raum mit dem Käfig gehen")
                            .buildMenu()
            )

    fun askTheTroll() {
      console.print(
              "Der Troll sagt'Ach ich hätte so gerne einen Keks.Aber der Koch will mir keinen Keks geben. Hilfst du mir.'"
      )
      val choice =
              console.multipleChoice("Was machst Du?", listOf("Ihn helfen", "Ihn nicht helfen"))
      fun helpTroll() {
        console.print(
                "Er zeigt dir den Weg zur Küche.Dort fragst du den Koch ob er dir einen Keks gibt.Er sagt:'Na gut aber nur wenn du diese Aufgabe löst." +
                        "An einem Hafen kommt ein Boot entweder nach 2,4,8,12 oder 16 Wochen.Nach wie vielen Wochen treffen sich alle Schiffe wieder am Hafen'"
        )
        val answer = console.ask("Was ist die Antwort?")
        if (answer.matchesExactly("48")) {
          console.print(
                  "Du sagst die Antwort und der Koch gibt dir den Keks!Den Keks gibst du den Troll der aufgergt auf dich wartet"
          )
        } else {
          console.print("Die Antwort ist falsch.Versuche es nochmal.")
          helpTroll()
        }
      }
      fun donHelpTroll() {
        console.print(
                "Der Troll wird dann sehr traurig.Du wolltest den Troll nicht traurig machen also hilfst du ihn doch.Der Troll wird sehr fröhlich ."
        )
        console.print(
                "\n Er zeigt dir den Weg zur Küche.Dort fragst du den Koch ob er dir einen Keks gibt.Er sagt:"
        )
        console.print("'Na gut aber nur wenn du diese Aufgabe löst.")
        console.print(
                "\nAn einem Hafen kommt ein Boot entweder nach 2,4,8,12 oder 16 Wochen.Nach wie vielen Wochen treffen sich alle Schiffe wieder am Hafen'"
        )
        val answer = console.ask("Was ist die Antwort?")
        if (answer.matchesExactly("48")) {
          console.print(
                  "Du sagst die Antwort und der Koch gibt dir den Keks!Den Keks gibst du den Troll der aufgergt auf dich wartet"
          )
        } else {
          console.print("Die Antwort ist falsch.Versuche es nochmal.")
          donHelpTroll()
        }
      }
      choice.on("Ihn helfen") { helpTroll() }.on("Ihn nicht helfen") {}

      progress.remember(StoryFlags.BECAME_FRIENDS_WITH_TROLL)
      describeHallway()
    }
    choice
            .on("Den Troll fragen warum er so grimmig guckt") { askTheTroll() }
            .on("Zur Richtung von den zwei Turen und der Treppe gehen") { doorAndStairs() }
            .on("Zurück zum Raum mit dem Käfig gehen") { backToPrison() }
  }

  fun backToPrison() {
    console.print(
            "Du und die Katze sitzt wieder in diesen furchtbar langweiligen Raum.Viel tun kann man nicht"
    )
    describeHallway()
  }
  fun doorAndStairs() {
    if (progress.didItHappen(StoryFlags.BECAME_FRIENDS_WITH_TROLL)) {
      val choice =
              console.multipleChoice(
                      "Wohin gehst du",
                      listOf("Zum Treppenhaus", "Zur 1.Tür", "Zur 2.Tür")
              )
      choice.on("Zum Treppenhaus") { stairWay() }.on("Zur 1.Tür") { door1() }.on("Zur 2.Tür") {
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
    val choice =
            console.multipleChoice(
                    "Was machst du?",
                    listOf(
                            "Gucken was in den Rucksack ist",
                            "Versuchen die Tür aufzumachen",
                            "Du schaust dich um"
                    )
            )
    choice
            .on("Gucken was in den Rucksack ist") { backPack() }
            .on("Versuchen die Tür aufzumachen") { openDoor() }
            .on("Du schaust dich um") { breakTime() }
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
    console.print(
            "Du versuchst die Tür aufzumachen. Aber egal wie hart du ziehst und drückst geht die Tür nicht auf.Du gibst nach einer Weile auf ,da deine Hände wehtun."
    )
    stairWay()
  }
  fun breakTime() {
    if (progress.didItHappen(StoryFlags.OPENED_THE_BAG)) {
      console.print(
              "Du schaust dich um auf den Boden findest du ein Zettel aber nur eine Hälfte von einem Zettel.Du steckst den Zettel in deiner Hosentasche . An der Tür ist ein Bildschirm ." +
                      "\nAuf den Bildschirm steht:'Eine kaputte Uhr läuft doppelt so schnell wie eine normale Uhr. Wenn die kaputte Uhr um 7:45 angeschaltet wird welche Uhrzeit zeigt die kaputte Uhr wenn es 14:45 ist'"
      )
      val answer = console.ask("Was ist die Antwort?")
      if (answer.matchesExactly("21:45")) {
        console.print("Du sagst die Antwort und die Tür öffnet sich")
      } else {
        console.print("Die Antwort ist falsch.Versuche es nochmal.")
        breakTime()
      }
    } else {

      console.print(
              "Man kann nichts außer schwarz sehen.Sogar mit 100 Glühwürmchen sieht man nichts.Es ist komplett dunkel"
      )
    }
    progress.remember(StoryFlags.STAIRCASE_DONE)
    doorAndStairs()
  }
}

fun door1() {
  console.print(
          "Du gehst zur Tür und machst Sie auf . Es is ganz leer im Raum außer für den Schrank. Du gehst zum Schrank. Du ziehst an der Tür, aber sie geht nicht auf." +
                  "\nAm Schrank klebt ein Zettel dadrauf steht :'In meiner Schublade sind 10 rote und 10 gelbe Socken wie viele muss ich mindestens mit meinen Augen zu ziehen damit ich immer von einer Farbe mindestens 2 habe'"
  )
  val answer = console.ask("Was ist die Antwort?")
  if (answer.matchesExactly("3")) {
    console.print(
            "Du sagst die Antwort und der Schrank öffnet sich.Da drinne ist ein halber Zettel"
    )
  } else {
    console.print("Die Antwort ist falsch.Versuche es nochmal.")
    door1()
  }
  Hallway.doorAndStairs()
  progress.remember(StoryFlags.DOOR_ONE_DONE)
}

fun door2() {
  console.print(
          "Du gehst in den Raum rein.Hinten im Raum ist eine Zwerg . Er hat ein Schlüssel in der Hand . Er sagt nur ein Wort :'Passwort!Passwort!Passwort!...' "
  )
  if (progress.didItHappen(StoryFlags.STAIRCASE_DONE) &&
                  progress.didItHappen(StoryFlags.DOOR_ONE_DONE)
  ) {
    val choice =
            console.multipleChoice(
                    "Was machst du?",
                    listOf(
                            "Du bietest dein Sandwich dem Zwerg an",
                            "Versuchen mit dem Zwerg zu sprechen",
                            "Die zwei Zettel anschauen die du gefunden hast"
                    )
            )
    choice
            .on("Du bietest dein Sandwich dem Zwerg an") { giveSandwich() }
            .on("Versuchen mit dem Zwerg zu sprechen") { talkToZwi() }
            .on("Die zwei Zettel anschauen die du gefunden hast") { readTwoPapers() }
  } else {
    console.print(
            "Du hast keine Ahnung was der Zwerg meint. Vielleicht gehst du irgendwo anders hin bevor der Zwerg komplett ausflippt."
    )
  }
}

fun giveSandwich() {
  if (progress.didItHappen(StoryFlags.ANSWERED_THE_QUESTION)) {
    console.print("Der Zwerg nimmt dein Sandwich gerne an.Er isst es glücklich auf.")
    progress.remember(StoryFlags.FRIEND_WITH_ZWE)
  } else {
    console.print("Er sagt nur:'Passwort!Passwort!Passwort!...'")
  }
}

fun talkToZwi() {
  if (progress.didItHappen(StoryFlags.ANSWERED_THE_QUESTION) &&
                  progress.didItHappen(StoryFlags.FRIEND_WITH_ZWE)
  ) {
    console.print("Er sagt:")
    console.print(
            "\"Der böse Zauberer hat mich unter einen Fluch gesetzt , aber jetzt hast du mich befreit.Ich dachte ich würde für immer so bleiben. Hier ich lasse dich durch die Tür.\""
    )
    console.print("\nDu bedankst dich beim Zwerg und gehst weiter.")
    hallWay2()
  } else if (progress.didItHappen(StoryFlags.ANSWERED_THE_QUESTION)) {
    console.print("Er guckt dich an.Er sagt nichts.Aber hat aufgehört 'Passwort' zu sagen")
  } else {
    console.print("Er sagt nur:'Passwort!Passwort!Passwort!...'")
  }
}

fun readTwoPapers() {
  console.print(
          "Du tust die beiden Hälften zusammen und du erkennst einen Text ." +
                  "\n Der lautet:'Ein Riesenrad hat 28 Gondeln.In einer Gondel können maximal 7 Leute mitfahren.Wie viele Leute können maximal mitfahren wenn 4 Gondeln kaputt sind'"
  )
  val answer = console.ask("Was ist die Antwort?")
  if (answer.matchesExactly("168")) {
    console.print("Du sagst die Antwort und der Zwerg sagt 'richtig' aber mehr nicht")
  } else {
    console.print("Die Antwort ist falsch.Versuche es nochmal.")
    readTwoPapers()
  }
  progress.remember(StoryFlags.ANSWERED_THE_QUESTION)
}

fun hallWay2() {
  console.print(
          "Du gehst rein und du siehst 3 Türen und ein Weg zum Dachboden. Eine Tür ist sogar mit einem Schloss fest verschlossen"
  )
  val choice =
          console.multipleChoice(
                  "Wohin gehst du?",
                  listOf("Zur 1.Tür", "Zur 2.Tür", "Zum Dachboden")
          )
  choice.on("Zur 1.Tür") { RoomWithGiant.door3() }.on("Zur 2.Tür") { RoomWithGiant.door4() }.on(
                  "Zum Dachboden"
          ) { RoomWithGiant.Way_1() }
}

object RoomWithGiant : ColoredConsole {
  fun door3() {
    if (progress.didItHappen(StoryFlags.MET_ELF)) {
      console.print("Da ist nur ein leerer Raum und eine große Treppe. Hier gibt es nichts mehr.")
      hallWay2()
      return
    }
    console.print("Du gehst rein und du siehst einen riesigen Schuh! Er sagt: ")
    console.print("\"Hallo, komm' hoch zu mir, damit wir uns unterhalten können.\"".blue)
    choose()
  }
  private fun choose() {
    val choice =
            console.multipleChoice(
                    "Was tust Du?",
                    progress.createOptions()
                            .addOption("Den Schuh fragen, wo er drückt")
                            .unless(StoryFlags.MET_GIANT)
                            .addOption("Dich umschauen")
                            .inCase(StoryFlags.MET_GIANT)
                            .addOption("Die Treppe hoch gehen")
                            .inCase(StoryFlags.FOUND_STAIRS)
                            .buildMenu()
            )
    choice
            .on("Den Schuh fragen, wo er drückt") { talkToShoe() }
            .on("Dich umschauen") { lookAround() }
            .on("Die Treppe hoch gehen") { goToGiantsHead() }
  }
  fun room3() {
    if (progress.didItHappen(StoryFlags.FOUND_STAIRS)) {
      console.print("Da ist immer noch ein riesiger Schuh und eine Treppe nach oben")
    } else {
      console.print("Da ist immer noch ein riesiger Schuh.")
    }
    choose()
  }
  fun talkToShoe() {
    console.print("Der Schuh sagt: ")
    console.print("\"Hey! Ich bin doch kein Schuh! Ich bin ein Riese! Schau' mal nach oben.\"".blue)
    val choice =
            console.multipleChoice(
                    prompt = "Was machst Du?",
                    listOf("Nach oben gucken", "Den Schuh überzeugen, dass er ein Schuh ist")
            )
    choice.on("Den Schuh überzeugen, dass er ein Schuh ist") {
      console.print(
              "\"Aber Mr. Schuh! Ich sehe eindeutig, dass sie ein Schuh sind! Solche Schuhe wie Sie gibt es auf jeden Fall nicht bei Deichmann... Sie müssen ein Schuh sein!\"".blue
      )
      console.print("Der Schuch lacht ... ")
      console.print(
              "\"Den Schuch habe ich vom bösen Zauberer bekommen, damit hier nicht alles nach Käsefuß stinkt.\"".blue
      )
      console.print(". Du schaust nach oben.")
    }
    console.print(
            "Der Raum ist mindestens 100m hoch und gaaanz oben ahnst Du eine Nase! Das ist ein Riese! Aber er scheint ja nett zu sein"
    )
    progress.remember(StoryFlags.MET_GIANT)
    room3()
  }
  fun lookAround() {
    console.print(
            "Da ist noch ein Schrank in den Zimmer.Es ist braun und sehr , sehr staubig .Du öffnest den Schrank und du siehst eine Treppe die nach oben führt."
    )
    progress.remember(StoryFlags.FOUND_STAIRS)
    room3()
  }
  fun goToGiantsHead() {
    console.print(
            "Du gehst die Treppe hoch .Du gehst und gehst und gehst. Die Treppe ist sooooooooo... lang!Wenn du ENDLICH oben angekommen bist . Dort ist eine Tür. Auf der Tür ist ein Zettel.\nAuf den Zettel steht:"
    )
    console.print("'Wenn du rein gehst kommst du nie wieder raus'")
    val choice =
            console.multipleChoice(
                    prompt = "Was machst Du?",
                    listOf("Rein gehen", "Vor der Tür stehen bleiben", "Nach unten rennen")
            )
    choice
            .on("Rein gehen") {
              console.print("Du gehst rein und siehst eine riesige Nase.")
              console.print("Die Nase sagt :")
              console.print(
                      "\" Hallo.Schön dich kennenzulernen. Ich brauche dringend deine Hilfe.Ich bin eigentlich eine Elfe aber der böse Zauberer hat mich in ein Riese verwandelt\nBitte hilfe mir\"".blue
              )
              console.print(
                      "Natürlich wirst du ihm helfen.Also fragst du ihn wie du ihn helfen kannst.Er sagt :"
              )
              console.print(
                      "\"Neben dir ist ein kleiner Tresor dadrinne ist das gegenmittel .Aber man braucht dafür ein Passwort.\"".blue
              )
              console.print("Auf den Boden findest du ein Zettel.")
              ques()
            }
            .on("Vor der Tür stehen bleiben") {
              console.print("Lilli sagt:")
              console.print(
                      "\"Ach komm schon geh doch rein.Du musst das tun sonst kommen wir nicht weiter.Wenn du willst gehe ich vor.\"".purple
              )
              console.print("Du gehst durch die Tür eigentlich hattest du ja nicht mal eine Wahl")
              console.print("Du gehst rein und siehst eine riesige Nase.")
              console.print("Der Riese sagt :")
              console.print(
                      "\" Hallo.Schön dich kennenzulernen. Ich brauche dringend deine Hilfe.Ich bin eigentlich eine Elfe aber der böse Zauberer hat mich in ein Riese verwandelt\nBitte hilfe mir\"".blue
              )
              console.print(
                      "Natürlich wirst du ihm helfen.Also fragst du ihn wie du ihn helfen kannst.Er sagt :"
              )
              console.print(
                      "\"Neben die ist ein kleiner Tresor dadrinne ist das gegenmittel .Aber man braucht dafür ein Passwort.\"".blue
              )
              console.print("Auf den Boden findest du ein Zettel.")

              ques()
            }
            .on("Nach unten rennen") {
              console.print(
                      "Lilli sagt:\"Ach komm schon geh doch rein.Du musst das tun sonst kommen wir nicht weiter.Wenn du willst gehe ich vor.\""
              )
              console.print(
                      "Du gehst die Treppe hoch und durch die Tür du hattest ja eigentlich nicht mal eine Wahl"
              )
              console.print("Der Riese sagt :")
              console.print(
                      "\" Hallo.Schön dich kennenzulernen. Ich brauche dringend deine Hilfe.Ich bin eigentlich eine Elfe aber der böse Zauberer hat mich in ein Riese verwandelt\nBitte hilfe mir\"".blue
              )
              console.print(
                      "Natürlich wirst du ihm helfen.Also fragst du ihn wie du ihn helfen kannst.Er sagt :"
              )
              console.print(
                      "\"Neben dir ist ein kleiner Tresor dadrinne ist das gegenmittel .Aber man braucht dafür ein Passwort.\"".blue
              )
              console.print("Auf den Boden findest du ein Zettel.")
              ques()
            }
  }
  fun ques() {
    console.print(
            "Dadrauf steht :'Du kaufst ein sehr güstiges Buch und ein sehr güstigen Stift. Beides zusammen kostet 1 Euro und 10 cents. Das Buch kostet 1 Euro mehr als der Stift.Wie viele cents kostet der Stift?'"
    )
    val answer = console.ask("Welche Antwort tippst du ein?")
    if (answer.matchesExactly("5")) {
      console.print(
              "Du sagst die Antwort und der Tresor öffnet sich.Du gibst dem Riese das gegenmittel.Er freut sich und sagt :"
      )
      console.print("\"Danke!Danke!Danke!Danke!Danke!\"".blue)
      console.print("\n.Dann trinkt er das Gegenmittel.Er verwandelt sich in ein Elf .Er sagt:")
      console.print(
              "\"Ich komme mit dir mit.Heb den Tresor mal auf da solltest du einen Knopf finden worauf du drücken musst,denn dort ist ein Ausgang\"".blue
      )
      console.print("Du machst das und es stimmt .Ihr geht zusammen raus.")
      progress.remember(StoryFlags.MET_ELF)
    } else {
      console.print("Die Antwort ist falsch.Versuche es nochmal.")
      ques()
    }
    hallWay2()
  }
  fun door4() {
    console.print("Hinten im Raum sitzt ein Einhorn.Vor dem Einhorn ist ein Lasergitter.Sie sagt :")
    console.print("\"Hi , Ich bin schon so lange eingespert .Hilfst du mir bitte!\"")
    console.print("\nDu nickst und fragst:")
    console.print("\"Wie kann ich helfen\"".cyan)
    console.print(".Sie sagt:")
    console.print(
            "\"Neben dir ist ein sehr kleiner Gang .Dadrinne sollte ein aus Knopf sein.Kennst du vielleicht jemand der klein ist?\"".yellow
    )
    if (progress.didItHappen(StoryFlags.MET_ELF)) {
      console.print("Die Elfe sagt :")
      console.print("\"Ich kann das machen.Ich passe nämlich durch das Loch\"".blue)
      console.print("Du stimmst zu und die Elfe geht durch das Loch.Sie ruft :\"")
      console.print("Du brauchst ein Passwort hier ist ein Zettel\"".blue)
      quasi()
    } else {
      console.print(
              "Du kennst keinen der durch das winzige Loch passt also kannst du ihr leider nicht helfen .Du versprichts ihr wieder zu kommen"
      )
      hallWay2()
    }
  }
  fun quasi() {
    console.print(
            "\"Dadrauf steht:'Der Vater ist 35 Jahre alt und sein Sohn ist 5.In wie vielen Jahren wird der Vater genau dreimal so alt sein wie der Sohn?\"".blue
    )
    val answer = console.ask("Was ist die Antwort?")
    if (answer.matchesExactly("10")) {
      console.print("Die Elfe ruft:")
      console.print("\"Die Antwort ist richtig.Ich drücke jetzt den Knopf!\"".blue)
      console.print(
              "BIEP!Das Lasergitter ist verschwunden!Das Einhorn kommt raus und bedankt sich.Sie kommt auch mit euch mit."
      )
      progress.remember(StoryFlags.MET_UNICORN)
      hallWay2()
    } else {
      console.print("Die Antwort ist falsch. Versuche es nochmal.")
      quasi()
    }
  }
  fun Way_1() {
    if (progress.didItHappen(StoryFlags.MET_ELF) && progress.didItHappen(StoryFlags.MET_UNICORN)) {
      console.print("Bevor du hoch gehst steht der Troll vor der Treppe. Er sagt : ")
      console.print("\"Ich dachte du könntest ein bisschen Hilfe gebrauchen.\"".green)
      console.print(
              "\nDu bedankst dich ,denn je mehr Helfer ,desto besser!Ihr geht hoch und geht in der einzigen Tür dort rein.Drinne ist ein Zettel.Da drauf steht mal kein Knobelaufgabe.Es steht nämlich:\""
      )
      console.print(
              "\n\"Im Raum ist ein wichtiger Schlüssel.Aber er ist nicht ganz.Ein Teil vielleicht in der Schüssel.Oder beim Teddybär Franz\"Lilli erwidert:"
      )
      console.print("\"Das ist ja ein Reim\"".purple)
      console.print("\"Stimmt, das ist mir noch nicht aufgefallen!\"".yellow)
      console.print(",sagte das Einhorn überrascht!Danach geht ihr rein.")
      watch()
    } else if (progress.didItHappen(StoryFlags.MET_ELF)) {
      console.print("")
    }
  }
  fun watch() {
    console.print(
            "Dieses Zimmer ist das einzigste Zimmer das ein bisschen interessant ist.Im Raum hängt ein Seil von der Decke und wenn man genauer hinguckt ist oben vom Seil ein Schlüsselteil." +
                    "\nGanz unten im Raum sieht man ein kleines,schmales Lochund da drinne sieht man ein Schlüsselteil.Auf der anderen Seite des Raumes ist ein Haufen von Sachen." +
                    "\nZum Beispiel alte Spielzeuge,altes Küchenzeugs,alte Klamotten und so weiter.Ist vielleicht ein Schlüssel dort?Und natürlich gibt es eine Box das ein Passwort braucht." +
                    "\nDaneben ist ein Zettel mit einer Knobel aufgabe.Du erzählst den anderen wo die Schlüssel sind oder sein können.Lilli sagt :\""
    )
    console.print(
            "\nIch kann gut Seile hochklettern.Un da du das Mathe-Genie bist kannst du ja die Knobel aufgabelösen".purple
    )
    console.print("\".Alle stimmen zu.Die Elfe sagt:\"")
    console.print("Ich bin sehr klein und ich könnte ins winzige Loch passen\"".blue)
    console.print("\"Ich kann sehr gut riechen,deshalb kann ich in den alten Sachen gucken\"".green)
    console.print(",sagte der Troll.\nDa ihr jetzt einen Plan habt fängt ihr an.")
    console.print("Auf den Zettel steht:")
    quan()
  }
  fun quan() {
    console.print(
            "'Zwei Freunde kriegen 1 mal pro Woche Taschengeld.Die ältere von denen kriegt zwei Euro mehr als die andere.Zusammen kreigen sie 12 Euro.Wie viel kriegt die jüngere von denen.(Schreibe nur die Zahl hin)'"
    )
    val answer = console.ask("Was ist die Antwort?")
    if (answer.matchesExactly("5")) {
      console.print(
              "Du tippst die Antwort rein und da drinne ist natürlich ein Schlüsselteil.In zwischen haben die Katze , der Troll und die Elfe ein Stück." +
                      "\nIhr guckt , ob die Teile passen und sie ... PASSEN!lILLI FRAGT.\""
      )
      console.print("Aber wie kriegen wir sie zusammen damit wir sie benutzen können?".purple)
      console.print("\"Darauf hin antwortete das Einhorn:\"")
      console.print(
              "Da kann ich helfen.Meine Magie kann zwar nicht vieles aber einen Schlüssel zusammen zaubern kann ich!".yellow +
                      "\".Und plötzlich wurde aus 4 Teilchen eins!Ihr habt es geschafft!"
      )
    } else {
      console.print("Die Antwort ist leider nicht richtig.Versuche es noch einmal.")
      quan()
    }
  }
}

object BossFight : ColoredConsole {
  private var challengesCompleted: Int = 0
  private var questions: List<List<String>> =
          listOf(
                  listOf("23 plus 12", "35"),
                  listOf("100 minus 43", "57"),
                  listOf("7 mal 12", "84"),
                  listOf("90 geteilt durch 6", "15"),
                  listOf("75 plus 13", "88"),
                  listOf("29 minus 13", "16"),
                  listOf("5 mal 17", "85"),
                  listOf("12 geteilt durch 4", "3"),
                  listOf("45 plus 38", "83"),
                  listOf("99 minus 53", "46"),
                  listOf("3 mal 15", "45"),
                  listOf("88 geteilt durch 8", "11"),
          )
  val reactions: List<String> =
          listOf(
                  "Pah! Das war doch Anfängerglück".red,
                  "Du hast das bestimmt nur geraten".red,
                  "Diese Aufgabe hätte jeder gekonnt".red,
                  "Sogar ein Hund könnte das rechnen!".red,
                  "Das hat dir bestimmt jemand verraten!".red
          )
  val wizardMoods: List<String> = listOf("menacing", "angry", "laughing", "casting", "furious")
  val alreadyAnswered: MutableSet<Int> = HashSet<Int>()
  
  fun startBossFight() {
    console.clear()
    console.print("Du betrittst den Thronsaal des bösen Zauberers...".red)
    CharacterArtManager.showCharacter("wizard", "menacing")
    console.print("Der böse Zauberer: 'Endlich bist du hier, $name! Aber du wirst mich niemals besiegen!'".red)
    console.pause()
    challenge()
  }
  
  fun pickRandomQuestion(): List<String> {
    var questionIndex = 0
    do {
      questionIndex = Random.nextInt(0, questions.size)
    } while (alreadyAnswered.contains(questionIndex))
    return questions[questionIndex]
  }
  fun markAnswered(question: String) {
    questions.forEachIndexed { i, it ->
      if (it.get(0).equals(question)) {
        alreadyAnswered.add(i)
      }
    }
  }
  fun challenge() {
    if (areWeDone()) {
      console.clear()
      CharacterArtManager.showCharacter("wizard", "defeated")
      console.print("Der Zauberer fällt zu Boden: 'Nein! Das ist unmöglich! Du hast mich besiegt!'".red)
      console.pause()
      outro()
    }
    
    // Show random wizard mood before each question
    console.clear()
    val randomMood = wizardMoods[Random.nextInt(wizardMoods.size)]
    CharacterArtManager.showCharacter("wizard", randomMood)
    
    val pair = pickRandomQuestion()
    val question = pair.first()
    val answer = pair.last()
    console.print("Der Zauberer stellt dir eine Aufgabe: $question".red)
    
    if (console.askRaw("Deine Antwort: ").matchesExactly(answer)) {
      console.print(reactions[challengesCompleted])
      challengesCompleted++
      markAnswered(question)
      console.pause()
    } else {
      console.clear()
      CharacterArtManager.showCharacter("wizard", "laughing")
      console.print("Falsch! So wirst Du mich nie besiegen, $name! HAHAHA!".red)
      console.pause()
    }
    challenge()
  }
  fun areWeDone(): Boolean {
    return challengesCompleted > 4
  }

  fun outro() {
    CharacterArtManager.showVictory()
    console.print("Du hast das Matheschloss befreit!")
    console.print("Alle deine Freunde sind jetzt frei!")
    println()
    console.pause("")
    console.clear()
    CharacterArtManager.showCharacter("lilli", "excited")
    console.print("Lilli: 'Danke $name! Du bist der beste!'".purple)
    console.pause()
    exitProcess(0)
  }
}


fun main() {
  // Example: Show title screen and demonstrate character art
  CharacterArtManager.showTitle()
  console.pause()
  
  // Example interaction demonstrating different moods
  console.clear()
  console.print("Du begegnest Lilli zum ersten Mal...")
  println()
  CharacterArtManager.showCharacter("Lilli", "curious")
  console.print("Die Katze schaut dich neugierig an.")
  console.pause()
  
  console.clear()
  console.print("Nachdem du dich vorgestellt hast...")
  println()
  CharacterArtManager.showCharacter("Lilli", "happy")
  console.print("Lilli freut sich, dich kennenzulernen!")
  console.pause()
  
  // Start the actual game
  console.clear()
  BossFight.startBossFight()
}
