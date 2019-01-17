package controlcenter.service.impl.alternative

import controlcenter.constant.SETTING_ASSISTANT_NAME
import controlcenter.service.AlternativeOptionsProvider
import controlcenter.service.SettingService
import org.springframework.stereotype.Service

@Service
class AssistantNameAlternativeOptionsProvider(private val settingService: SettingService) : AlternativeOptionsProvider {

    override fun getAlternativeOptions(commandText: String): Set<String> {
        val assistantName = settingService.get(SETTING_ASSISTANT_NAME).toLowerCase()
        val hasAssistantName = commandText.isStartsWithAssistantName(assistantName)

        return if (hasAssistantName) setOf(commandText.withoutAssistantName(assistantName))
        else emptySet()
    }

    private fun String.withoutAssistantName(assistantName: String) =
            this.replace(assistantName, "").trimStart()

    private fun String.isStartsWithAssistantName(assistantName: String): Boolean {
        val adoptedText = this.trimStart().toLowerCase()
        return adoptedText.startsWith(assistantName)
    }
}