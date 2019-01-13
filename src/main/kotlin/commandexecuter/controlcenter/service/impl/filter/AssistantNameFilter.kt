package commandexecuter.controlcenter.service.impl.filter

import commandexecuter.controlcenter.constant.SETTING_ASSISTANT_NAME
import commandexecuter.controlcenter.service.SettingService
import commandexecuter.controlcenter.service.TextFilter
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service

@Service
class AssistantNameFilter(private val settingService: SettingService) : TextFilter {
    private val log = LogManager.getLogger(this.javaClass)

    override fun filter(text: String): String {
        val assistantName = settingService.get(SETTING_ASSISTANT_NAME).toLowerCase()
        log.debug("Try to check and remove assistant name '$assistantName' from text '$text'")

        return if (isStartsWithAssistantName(text, assistantName))
            removeAssistantName(text, assistantName)
        else ""
    }

    private fun removeAssistantName(text: String, assistantName: String) =
            text.replace(assistantName, "").trimStart()

    private fun isStartsWithAssistantName(text: String, assistantName: String): Boolean {
        val adoptedText = text.trimStart().toLowerCase()
        return adoptedText.startsWith(assistantName)
    }
}