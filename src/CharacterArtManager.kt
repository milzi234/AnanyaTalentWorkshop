object CharacterArtManager {
    private val console = Console()
    
    /**
     * Shows a character with a specific mood.
     */
    fun showCharacter(character: String, mood: String) {
        val art = when (character.lowercase() to mood.lowercase()) {
            // Lilli
            "lilli" to "happy" -> Characters.LILLI_HAPPY
            "lilli" to "curious" -> Characters.LILLI_CURIOUS
            "lilli" to "worried" -> Characters.LILLI_WORRIED
            "lilli" to "excited" -> Characters.LILLI_EXCITED
            
            // Troll
            "troll" to "grumpy" -> Characters.TROLL_GRUMPY
            "troll" to "happy" -> Characters.TROLL_HAPPY
            "troll" to "thankful" -> Characters.TROLL_THANKFUL
            
            // Zwerg
            "zwerg" to "obsessed" -> Characters.ZWERG_OBSESSED
            "zwerg" to "confused" -> Characters.ZWERG_CONFUSED
            "zwerg" to "satisfied" -> Characters.ZWERG_SATISFIED
            
            // Giant
            "giant" to "sad" -> Characters.GIANT_SAD
            "giant" to "hopeful" -> Characters.GIANT_HOPEFUL
            
            // Elf
            "elf" to "happy" -> Characters.ELF_HAPPY
            "elf" to "grateful" -> Characters.ELF_GRATEFUL
            
            // Unicorn
            "unicorn" to "trapped" -> Characters.UNICORN_TRAPPED
            "unicorn" to "free" -> Characters.UNICORN_FREE
            "unicorn" to "grateful" -> Characters.UNICORN_GRATEFUL
            
            // Wizard
            "wizard" to "menacing" -> Characters.WIZARD_MENACING
            "wizard" to "angry" -> Characters.WIZARD_ANGRY
            "wizard" to "laughing" -> Characters.WIZARD_LAUGHING
            "wizard" to "casting" -> Characters.WIZARD_CASTING
            "wizard" to "furious" -> Characters.WIZARD_FURIOUS
            "wizard" to "defeated" -> Characters.WIZARD_DEFEATED
            
            else -> null
        }
        
        art?.let {
            console.printAsciiArt(it.art)
            println() // Extra line after character art
        }
    }
    
    /**
     * Shows the game title screen.
     */
    fun showTitle() {
        console.clear()
        val titleArt = """
        ${"\u001B[35m"}╔══════════════════════════════════════════╗
        ║                                          ║
        ║           Matheschloss                   ║
        ║                                          ║
        ╚══════════════════════════════════════════╝${"\u001B[0m"}
        
                    ${"\u001B[33m"}    .*
                       /|\
                      / | \
                     /  |  \
                    /   |   \
                   /____|____\
                   |    |    |
                   |  🚪 |    |
                   |____|____|
                      |||
                   ___|||___
                  [_________]${"\u001B[0m"}
        """.trimIndent()
        
        console.printCentered(titleArt)
        println()
        console.printCentered("\u001B[36mEin magisches Textabenteuer\u001B[0m")
        println()
        println()
    }
    
    /**
     * Shows the victory screen.
     */
    fun showVictory() {
        console.clear()
        val victoryArt = """
        ${"\u001B[33m"}        ✨ ✨ ✨
                 \|/
               .-'*'-.
              /       \
             |  \   /  |
             |   \_/   |
              \       /
               '-...-'
                 |||
               __|_|__
              [_______]
        
        ╔══════════════════════╗
        ║                      ║
        ║    GESCHAFFT! 🎉     ║
        ║                      ║
        ╚══════════════════════╝${"\u001B[0m"}
        """.trimIndent()
        
        console.printCentered(victoryArt)
        println()
        console.printCentered("\u001B[32mDu hast den bösen Zauberer besiegt!\u001B[0m")
        println()
    }
    
    /**
     * Shows the game over screen.
     */
    fun showGameOver() {
        console.clear()
        val gameOverArt = """
        ${"\u001B[31m"}      .-${"\""}${"\""}${"\""}${"\""}${"\""}${"\""}-.
             /          \
            |   X    X   |
            |      >     |
            |   ______   |
             \          /
              '-......-'
        
        ╔══════════════════════╗
        ║                      ║
        ║    GAME OVER         ║
        ║                      ║
        ╚══════════════════════╝${"\u001B[0m"}
        """.trimIndent()
        
        console.printCentered(gameOverArt)
        println()
        console.printCentered("\u001B[31mDas Abenteuer ist zu Ende.\u001B[0m")
        println()
    }
    
    /**
     * Shows a transition screen between scenes.
     */
    fun showTransition(message: String = "") {
        console.clear()
        if (message.isNotEmpty()) {
            console.printCentered("\u001B[36m$message\u001B[0m")
            println()
            println()
        }
        console.pause()
    }
}