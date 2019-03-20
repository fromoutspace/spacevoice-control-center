package controlcenter.service

interface CommandTextFilter : Prioritized {
    fun filterText(commandText: String): String
}