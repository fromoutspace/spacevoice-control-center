package controlcenter.service

interface AlternativeOptionsProvider {
    fun getAlternativeOptions(commandText: List<String>): Set<List<String>>
}