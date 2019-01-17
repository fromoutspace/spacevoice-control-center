package controlcenter.service

interface AlternativeOptionsProvider {
    fun getAlternativeOptions(commandText: String): Set<String>
}