/**
 * This helps the game remember what important things have happened in the story.
 * Like checking off boxes on a list!
 */
class StoryProgress {

    // A list of things the game remembers happened.
    // We use a 'Set' so we don't remember the same thing twice.
    private val thingsRemembered: MutableSet<String> = mutableSetOf()

    /**
     * Tell the game to remember that something important happened.
     *
     * @param whatHappened A short description of what happened (like "MET_WIZARD").
     */
    fun remember(whatHappened: String) {
        val added = thingsRemembered.add(whatHappened)
        // Optional: Print a message for debugging/learning
        // if (added) {
        //     println("[Game now remembers: $whatHappened]")
        // }
    }

    /**
     * Check if the game remembers that a specific thing happened.
     *
     * @param whatHappened The description of the thing we're asking about.
     * @return `true` if the game remembers it, `false` if it doesn't.
     */
    fun didItHappen(whatHappened: String): Boolean {
        return thingsRemembered.contains(whatHappened)
    }

    /**
     * Check if the game remembers ALL of the things in a list.
     * Useful for puzzles where you need multiple steps done.
     *
     * @param thingsToCheck A list of descriptions to check.
     * @return `true` if the game remembers ALL of them, `false` otherwise.
     */
    fun didAllTheseHappen(thingsToCheck: List<String>): Boolean {
        // Checks if the 'thingsRemembered' set contains every single item from 'thingsToCheck'
        return thingsRemembered.containsAll(thingsToCheck)
    }

    /**
     * Check if the game remembers AT LEAST ONE of the things in a list.
     * Useful if there are multiple ways to achieve something.
     *
     * @param thingsToCheck A list of descriptions to check.
     * @return `true` if the game remembers at least one of them, `false` otherwise.
     */
    fun didAnyOfTheseHappen(thingsToCheck: List<String>): Boolean {
        // Checks if *any* item in 'thingsToCheck' is also present in 'thingsRemembered'
        return thingsToCheck.any { thing -> thingsRemembered.contains(thing) }
    }

    /**
     * Tell the game to forget something (maybe it resets).
     * This might not be needed often in simple games.
     *
     * @param whatHappened The description of the thing to forget.
     */
     fun forget(whatHappened: String) {
         val removed = thingsRemembered.remove(whatHappened)
         if (removed) {
             println("[Game forgot: $whatHappened]")
         }
    }

    fun createOptions(): MenuBuilder {
        // Pass 'this' (the StoryProgress instance) to the builder
        return MenuBuilder(this)
    }
}

/**
 * Internal data class to hold potential options and their conditions.
 */
private data class PotentialOption(
    val text: String,
    val requiredFlags: MutableSet<String> = mutableSetOf(), // Flags that MUST be set
    val forbiddenFlags: MutableSet<String> = mutableSetOf()  // Flags that MUST NOT be set
)

/**
 * A fluent builder to construct dynamic option lists based on StoryProgress.
 * Created via `storyProgress.createOptions()`.
 */
class MenuBuilder(private val storyProgress: StoryProgress) {

    private val potentialOptions = mutableListOf<PotentialOption>()

    /**
     * Adds a potential option text to the menu.
     * Subsequent calls to `inCase` or `unless` will apply to this option.
     *
     * @param text The text of the option to potentially add.
     * @return This MenuBuilder instance for chaining.
     */
    fun addOption(text: String): MenuBuilder {
        potentialOptions.add(PotentialOption(text))
        return this // Return builder for chaining
    }

    /**
     * Adds a condition to the *last added option*: it will only appear
     * if the specified flag *is set* in the StoryProgress.
     * Can be chained with other `inCase` or `unless` calls for the same option.
     *
     * @param flag The StoryFlag that must be set.
     * @return This MenuBuilder instance for chaining.
     * @throws IllegalStateException if called before `addOption`.
     */
    fun inCase(flag: String): MenuBuilder {
        val lastOption = potentialOptions.lastOrNull()
            ?: throw IllegalStateException("Cannot call 'inCase' before adding an option with 'addOption'")
        lastOption.requiredFlags.add(flag)
        return this
    }

    /**
     * Adds a condition to the *last added option*: it will only appear
     * if the specified flag *is NOT set* in the StoryProgress.
     * Can be chained with other `inCase` or `unless` calls for the same option.
     *
     * @param flag The StoryFlag that must NOT be set.
     * @return This MenuBuilder instance for chaining.
     * @throws IllegalStateException if called before `addOption`.
     */
    fun unless(flag: String): MenuBuilder {
        val lastOption = potentialOptions.lastOrNull()
            ?: throw IllegalStateException("Cannot call 'unless' before adding an option with 'addOption'")
        lastOption.forbiddenFlags.add(flag)
        return this
    }

    /**
     * Evaluates all added options and their conditions against the StoryProgress.
     *
     * @return A list of strings containing only the options whose conditions are met.
     */
    fun buildMenu(): List<String> {
        val finalOptions = mutableListOf<String>()
        for (option in potentialOptions) {
            // Check if all required flags are present
            val requirementsMet = option.requiredFlags.all { flag ->
                storyProgress.didItHappen(flag)
            }
            // Check if none of the forbidden flags are present
            val restrictionsMet = option.forbiddenFlags.none { flag ->
                storyProgress.didItHappen(flag)
            }

            if (requirementsMet && restrictionsMet) {
                finalOptions.add(option.text)
            }
        }
        return finalOptions
    }
}
