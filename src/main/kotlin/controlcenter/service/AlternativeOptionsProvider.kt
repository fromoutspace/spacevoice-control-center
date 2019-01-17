package controlcenter.service

interface AlternativeOptionsProvider {
    fun getAlternativeOptions(commandText: String): Set<String>

    val alternativeOptionsMustNotBeEmpty: Boolean
        get() = false
}