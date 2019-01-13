package commandexecuter.controlcenter.service.impl.alternative

import commandexecuter.controlcenter.constant.SETTING_ASSISTANT_NAME
import commandexecuter.controlcenter.constant.SETTING_REQUIRE_ASSISTANT_NAME
import commandexecuter.controlcenter.service.AlternativeProvider
import commandexecuter.controlcenter.service.SettingService
import org.springframework.stereotype.Service

@Service
class AssistantNameAlternativeProvider(private val settingService: SettingService) : AlternativeProvider {
    override val alternativesRequiredNotBeEmpty = true

    override fun getAlternativeVariants(commandText: String): Set<String> {
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