/**
 * Represents the normalized result of a user prompt.
 * Provides convenience methods for checking the content.
 *
 * @property normalizedText The user's input, trimmed and converted to lowercase.
 */
data class PromptResult(val normalizedText: String) {

    fun on(pattern: String, block: () -> Unit): PromptResult {
        val matches = pattern.toRegex().containsMatchIn(this.normalizedText)
        if (matches) {
            block()
        }
        return this;
    }

    fun onExactly(pattern: String, block: () -> Unit): PromptResult {
        val matches = pattern.toRegex().matches(this.normalizedText)
        if (matches) {
            block()
        }
        return this;
    }


    /**
     * Provides direct access to the normalized text, often useful for comparisons or logging.
     */
    override fun toString(): String = normalizedText
}
