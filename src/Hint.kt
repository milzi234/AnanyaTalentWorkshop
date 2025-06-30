class Hint {

    private class ThresholdHint {
        val hintText: String = ""
        val threshold: Int = -1
    }
    private var errorCount: Int = 0
    private var hintText: String = ""
    private var hints: List<ThresholdHint> = ArrayList<ThresholdHint>()

    companion object {
        fun giveHint(hintText: String, threshold: Int): Hint {
            val hint = Hint()
            hint.giveHint(hintText, threshold )
            return hint
        }
    }

    fun giveHint(hintText: String, threshold: Int): Hint {

        return this
    }

    fun countError() {
        errorCount++
        pickCurrentHintText()
    }

    fun getHintText(): String {
        return "\n\n" + hintText;
    }

    private fun pickCurrentHintText() {

    }
}