package commandexecuter.controlcenter.service

interface AlternativeProvider {
    fun getAlternativeVariants(commandText: String): Set<String>
    val alternativesRequiredNotBeEmpty: Boolean
        get() = false
}