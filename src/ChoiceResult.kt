data class ChoiceResult(val choiceNumber: Int, val choiceText: String) {
    /**
     * Executes the given block if the ChoiceResult's number matches the specified choiceNumber.
     * Allows for chaining.
     *
     * @param targetChoiceNumber The 1-based choice number to match against.
     * @param block The code block to execute if the numbers match.
     * @return The original ChoiceResult instance, allowing for chaining `onChoice` calls.
     */
    fun on(targetChoiceNumber: Int, block: () -> Unit): ChoiceResult {
        if (this.choiceNumber == targetChoiceNumber) {
            block()
        }
        return this // Return this to allow chaining
    }

    /**
     * Executes the given block if the ChoiceResult's text matches the specified choiceText
     * (case-sensitive, exact match). Allows for chaining.
     *
     * @param targetChoiceText The exact string text to match against.
     * @param block The code block to execute if the texts match.
     * @return The original ChoiceResult instance, allowing for chaining `onChoice` calls.
     */
    fun on(targetChoiceText: String, block: () -> Unit): ChoiceResult {
        // Use exact string comparison as choiceText comes directly from the options list
        if (this.choiceText == targetChoiceText) {
            block()
        }
        return this // Return this to allow chaining
    }
}
