package commandexecuter.controlcenter.service

interface TextFilter {
    fun filter(text: String): String
}