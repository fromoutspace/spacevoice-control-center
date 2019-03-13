package controlcenter.service.impl.alternative

import controlcenter.constant.SETTING_ASSISTANT_NAME
import controlcenter.service.AlternativeOptionsProvider
import controlcenter.service.SettingService
import org.springframework.stereotype.Service

@Service
class AssistantNameAlternativeOptionsProvider(private val settingService: SettingService) : AlternativeOptionsProvider {

    override fun getAlternativeOptions(commandText: List<String>): Set<List<String>> {
        val assistantName = settingService.get(SETTING_ASSISTANT_NAME).toLowerCase()

        return if (commandText.isStartsWithAssistantName(assistantName))
            setOf(commandText.withoutAssistantName(assistantName))
        else
            emptySet()
    }

    private fun List<String>.isStartsWithAssistantName(assistantName: String) = this.firstOrNull() == assistantName

    private fun List<String>.withoutAssistantName(assistantName: String): List<String> = this.filterNot { it == assistantName }
}
