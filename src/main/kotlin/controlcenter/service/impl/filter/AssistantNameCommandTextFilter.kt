package controlcenter.service.impl.filter

import controlcenter.constant.SETTING_ASSISTANT_NAME
import controlcenter.constant.SETTING_REQUIRE_ASSISTANT_NAME
import controlcenter.service.CommandTextFilter
import controlcenter.service.SettingService
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component

@Component
class AssistantNameCommandTextFilter(private val settingService: SettingService) : CommandTextFilter {
    private val log = LogManager.getLogger(this.javaClass)

    override fun filterText(commandText: String): String {
        if (isNeedAssistantName().not()) return commandText

        val assistantNameLowercase = getAssistantNameLowercase()
        val commandTextLowercase = commandText.toLowerCase()
        val startsWithAssistantName = commandTextLowercase.startsWith(assistantNameLowercase)

        if (startsWithAssistantName) {
            log.debug("Drop assistant name=$assistantNameLowercase from text=$commandTextLowercase")
            return commandText.drop(assistantNameLowercase.length)
        } else {
            log.debug("Assistant name=$assistantNameLowercase not found in text=$commandTextLowercase")
            return ""
        }
    }

    private fun isNeedAssistantName() = settingService.isTrue(SETTING_REQUIRE_ASSISTANT_NAME)

    private fun getAssistantNameLowercase() = settingService.get(SETTING_ASSISTANT_NAME).toLowerCase()
}