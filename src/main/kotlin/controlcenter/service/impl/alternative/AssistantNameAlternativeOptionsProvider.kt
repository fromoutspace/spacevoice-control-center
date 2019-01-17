package controlcenter.service.impl.alternative

import controlcenter.constant.SETTING_ASSISTANT_NAME
import controlcenter.constant.SETTING_REQUIRE_ASSISTANT_NAME
import controlcenter.service.AlternativeOptionsProvider
import controlcenter.service.SettingService
import org.springframework.stereotype.Service

@Service
class AssistantNameAlternativeOptionsProvider(private val settingService: SettingService) : AlternativeOptionsProvider {
    override val alternativeOptionsMustNotBeEmpty = true

    override fun getAlternativeOptions(commandText: String): Set<String> {
        val assistantName = settingService.get(SETTING_ASSISTANT_NAME).toLowerCase()
        val hasAssistantName = commandText.isStartsWithAssistantName(assistantName)
        val isAssistantNameRequired = settingService.get(SETTING_REQUIRE_ASSISTANT_NAME) == "true"

        return if (isAssistantNameRequired && hasAssistantName.not()) emptySet()
        else setOf(commandText, commandText.withoutAssistantName(assistantName))
    }

    private fun String.withoutAssistantName(assistantName: String) =
            this.replace(assistantName, "").trimStart()

    private fun String.isStartsWithAssistantName(assistantName: String): Boolean {
        val adoptedText = this.trimStart().toLowerCase()
        return adoptedText.startsWith(assistantName)
    }
}