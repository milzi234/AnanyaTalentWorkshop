/**
 * Represents the normalized result of a user prompt.
 * Provides convenience methods for checking the content.
 *
 * @property normalizedText The user's input, trimmed and converted to lowercase.
 */
data class PromptResult(val normalizedText: String) {

    fun on(pattern: String, block: () -> Unit): PromptResult {
        val matches = matches(pattern)
        if (matches) {
            block()
        }
        return this;
    }

    fun onExactly(pattern: String, block: () -> Unit): PromptResult {
        val matches = matchesExactly(pattern)
        if (matches) {
            block()
        }
        return this;
    }

    fun matches(pattern: String): Boolean {
        return pattern.toRegex().containsMatchIn(this.normalizedText)
    }

    fun matchesExactly(pattern: String): Boolean {
        return pattern.toRegex().matches(this.normalizedText)
    }


    /**
     * Provides direct access to the normalized text, often useful for comparisons or logging.
     */
    override fun toString(): String = normalizedText
}
