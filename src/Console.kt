class Console {
    /**
     * Prompts the user with a question and presents multiple choices.
     * Repeats the prompt until a valid choice number is entered.
     *
     * @param prompt The question or statement to display before the choices.
     * @param options A list of strings representing the available choices.
     * @return The string value of the chosen option.
     * @throws IllegalArgumentException if the options list is empty.
     */
    fun multipleChoice(prompt: String, options: List<String>): ChoiceResult {
        require(options.isNotEmpty()) { "Cannot ask multiple choice with zero options." }

        println(prompt)
        options.forEachIndexed { index, option ->
            println("${index + 1}. $option") // Display options 1-based
        }

        while (true) {
            print("> ") // Input prompt indicator
            val input = readlnOrNull()

            if (input.isNullOrBlank()) {
                println("Bitte wähle eine Antwort aus.")
                continue
            }

            val choiceIndex = input.toIntOrNull()
            if (choiceIndex == null) {
                println("Bitte gib eine Zahl ein, die deiner Auswahl entspricht.")
                continue
            }

            // Adjust from 1-based user input to 0-based list index
            if (choiceIndex in 1..options.size) {
                return ChoiceResult(choiceIndex, options[choiceIndex - 1])
            } else {
                println("Die Zahl gibt es nicht. Bitte wähle eine Option zwischen 1 und ${options.size}.")
            }
        }
    }

    /**
     * Prompts the user with a question, reads their input, normalizes it
     * (lowercase, trimmed), and returns it wrapped in a PromptResult object.
     *
     * @param prompt The question or statement to display to the user.
     * @return A PromptResult containing the normalized user input.
     */
    fun ask(prompt: String): PromptResult {
        println(prompt)
        print("> ")
        val rawInput = readlnOrNull().orEmpty()
        val normalizedInput = rawInput.trim().lowercase()
        return PromptResult(normalizedInput)
    }

    /**
     * Prompts the user with a question, reads their input, normalizes it
     * (lowercase, trimmed), and returns it wrapped in a PromptResult object.
     *
     * @param prompt The question or statement to display to the user.
     * @return A PromptResult containing the normalized user input.
     */
    fun askRaw(prompt: String): PromptResult {
        println(prompt)
        print("> ")
        val rawInput = readlnOrNull().orEmpty()
        return PromptResult(rawInput)
    }

    fun print(text: String) {
        println(text);
    }

    fun printAndWait(text: String) {
        println(text)
        readLine()
    }
}