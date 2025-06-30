data class CharacterArt(
    val character: String,
    val mood: String,
    val art: String
)

object Characters {
    // Lilli (Cat) ASCII Art
    val LILLI_HAPPY = CharacterArt(
        "Lilli", 
        "happy",
        """
        ${"\u001B[35m"}    /\_/\  
           ( o.o ) 
            > ^ <  ♪
           /|   |\
          / |___| \
         (_/     \_)${"\u001B[0m"}
        """.trimIndent()
    )
    
    val LILLI_CURIOUS = CharacterArt(
        "Lilli",
        "curious", 
        """
        ${"\u001B[35m"}    /\_/\  
           ( O.O ) ?
            > ^ <  
           /|   |\
          / |___| \
         (_/     \_)${"\u001B[0m"}
        """.trimIndent()
    )
    
    val LILLI_WORRIED = CharacterArt(
        "Lilli",
        "worried",
        """
        ${"\u001B[35m"}    /\_/\  
           ( >.< ) 
            > ~ <  
           /|   |\
          / |___| \
         (_/     \_)${"\u001B[0m"}
        """.trimIndent()
    )
    
    val LILLI_EXCITED = CharacterArt(
        "Lilli",
        "excited",
        """
        ${"\u001B[35m"}    /\_/\  
           ( ^.^ ) !!!
            > ^ <  
          \|   |/
           |___|
          /     \
         (_)   (_)${"\u001B[0m"}
        """.trimIndent()
    )
    
    // Troll ASCII Art
    val TROLL_GRUMPY = CharacterArt(
        "Troll",
        "grumpy",
        """
        ${"\u001B[32m"}     .-${"\""}${"\""}-.
            /  ò ó \
           |   __   |
            \ (--) /
             |~~~~|
            /|    |\
           / |    | \
          (  |    |  )
           \_|____|_/${"\u001B[0m"}
        """.trimIndent()
    )
    
    val TROLL_HAPPY = CharacterArt(
        "Troll",
        "happy",
        """
        ${"\u001B[32m"}     .-${"\""}${"\""}-.
            /  ^ ^ \
           |   __   |
            \ \__/ /
             |~~~~|
            /|    |\
           / |    | \
          (  |    |  )
           \_|____|_/${"\u001B[0m"}
        """.trimIndent()
    )
    
    val TROLL_THANKFUL = CharacterArt(
        "Troll",
        "thankful",
        """
        ${"\u001B[32m"}     .-${"\""}${"\""}-.
            /  * * \  ♥
           |   __   |
            \ \__/ /
             |~~~~|
            /|    |\
           / |    | \
          (  |    |  )
           \_|____|_/${"\u001B[0m"}
        """.trimIndent()
    )
    
    // Zwerg (Dwarf) ASCII Art
    val ZWERG_OBSESSED = CharacterArt(
        "Zwerg",
        "obsessed",
        """
        ${"\u001B[33m"}    _____
           /     \
          | @ @ @ |
          |   >   |
          | ----- |
           \|||||/
            |   |
           /|   |\
          / |   | \
         (__|___|__)${"\u001B[0m"}
        """.trimIndent()
    )
    
    val ZWERG_CONFUSED = CharacterArt(
        "Zwerg",
        "confused",
        """
        ${"\u001B[33m"}    _____
           /     \
          | ? _ ? |
          |   >   |
          | ~~~~~ |
           \|||||/
            |   |
           /|   |\
          / |   | \
         (__|___|__)${"\u001B[0m"}
        """.trimIndent()
    )
    
    val ZWERG_SATISFIED = CharacterArt(
        "Zwerg",
        "satisfied",
        """
        ${"\u001B[33m"}    _____
           /     \
          | ^ _ ^ |
          |   >   |
          | \___/ |
           \|||||/
            |   |
           /|   |\
          / |   | \
         (__|___|__)${"\u001B[0m"}
        """.trimIndent()
    )
    
    // Giant ASCII Art
    val GIANT_SAD = CharacterArt(
        "Giant",
        "sad",
        """
        ${"\u001B[34m"}      ___
             /o o\
            |  _  |
            | /_\ |
             \___/
              |||
              |||
             /|||\
            / ||| \
           /  |||  \
          |   |||   |
          |___|_|___|
          [___] [___]${"\u001B[0m"}
        """.trimIndent()
    )
    
    val GIANT_HOPEFUL = CharacterArt(
        "Giant",
        "hopeful",
        """
        ${"\u001B[34m"}      ___
             /^ ^\
            |  _  |
            | \_/ |
             \___/
              |||
              |||
             /|||\
            / ||| \
           /  |||  \
          |   |||   |
          |___|_|___|
          [___] [___]${"\u001B[0m"}
        """.trimIndent()
    )
    
    // Elf ASCII Art
    val ELF_HAPPY = CharacterArt(
        "Elf",
        "happy",
        """
        ${"\u001B[36m"}     /\_/\
            ( ^.^ )
           < |   | >
            /| * |\
           / |___| \
          (  /   \  )
           \/     \/${"\u001B[0m"}
        """.trimIndent()
    )
    
    val ELF_GRATEFUL = CharacterArt(
        "Elf",
        "grateful",
        """
        ${"\u001B[36m"}     /\_/\
            ( *.* ) ♥
           < |   | >
            /| * |\
           / |___| \
          (  /   \  )
           \/     \/${"\u001B[0m"}
        """.trimIndent()
    )
    
    // Unicorn ASCII Art
    val UNICORN_TRAPPED = CharacterArt(
        "Unicorn",
        "trapped",
        """
        ${"\u001B[33m"}      ,-.
             / \  \
            |   \_/
            |  -.- |
            |   |   |
             \  |  /
            __|\_/|__
           |_________|
           ||| ||| |||
           ||| ||| |||${"\u001B[0m"}
        """.trimIndent()
    )
    
    val UNICORN_FREE = CharacterArt(
        "Unicorn",
        "free",
        """
        ${"\u001B[33m"}      ,-.
             / \  \
            |   \_/
            |  ^.^ |
            |   |   |
             \  |  /
              |\_/|
             /     \
            |   |   |
           (_) (_) (_)${"\u001B[0m"}
        """.trimIndent()
    )
    
    val UNICORN_GRATEFUL = CharacterArt(
        "Unicorn",
        "grateful",
        """
        ${"\u001B[33m"}      ,-.
             / \  \
            |   \_/
            |  *.* | ♥
            |   |   |
             \  |  /
              |\_/|
             /     \
            |   |   |
           (_) (_) (_)${"\u001B[0m"}
        """.trimIndent()
    )
    
    // Evil Wizard ASCII Art
    val WIZARD_MENACING = CharacterArt(
        "Wizard",
        "menacing",
        """
        ${"\u001B[31m"}      /\
             /  \
            /    \
           | >  < |
           |  ><  |
           | \__/ |
            \    /
           __||||__
          /  ||||  \
         |   ||||   |
         |___||||___|
           | || || |
          (_)||_||(_)${"\u001B[0m"}
        """.trimIndent()
    )
    
    val WIZARD_ANGRY = CharacterArt(
        "Wizard",
        "angry",
        """
        ${"\u001B[31m"}      /\
             /  \
            /    \
           | \  / |
           |  ><  |
           | /--\ |
            \    /
           __||||__
          /  ||||  \
         |   ||||   |
         |___||||___|
           | || || |
          (_)||_||(_)${"\u001B[0m"}
        """.trimIndent()
    )
    
    val WIZARD_LAUGHING = CharacterArt(
        "Wizard",
        "laughing",
        """
        ${"\u001B[31m"}      /\
             /  \
            /    \
           | ^  ^ |
           |  ><  |
           | \__/ |
            \    /  HA HA!
           __||||__
          /  ||||  \
         |   ||||   |
         |___||||___|
           | || || |
          (_)||_||(_)${"\u001B[0m"}
        """.trimIndent()
    )
    
    val WIZARD_CASTING = CharacterArt(
        "Wizard",
        "casting",
        """
        ${"\u001B[31m"}      /\
             /  \     *✧･ﾟ
            /    \  ✧･ﾟ*
           | ◉  ◉ |
           |  ><  |
           | \__/ |
            \    /
           __||||__
         ✧/  ||||  \✧
         |   ||||   |
         |___||||___|
           | || || |
          (_)||_||(_)${"\u001B[0m"}
        """.trimIndent()
    )
    
    val WIZARD_FURIOUS = CharacterArt(
        "Wizard",
        "furious",
        """
        ${"\u001B[31m"}      /\
             /  \
            /!!!!!\
           | ò  ó |
           |  ><  |
           | /▼▼\ |
            \    /
           __||||__
          /  ||||  \
         |   ||||   |
         |___||||___|
           | || || |
          (_)||_||(_)${"\u001B[0m"}
        """.trimIndent()
    )
    
    val WIZARD_DEFEATED = CharacterArt(
        "Wizard",
        "defeated",
        """
        ${"\u001B[31m"}      /\
             /  \
            /    \
           | x  x |
           |  __  |
           | /  \ |
            \    /
           __||||__
          /  ||||  \
         |   ||||   |
         |___||||___|
           | || || |
          (_)||_||(_)${"\u001B[0m"}
        """.trimIndent()
    )
}