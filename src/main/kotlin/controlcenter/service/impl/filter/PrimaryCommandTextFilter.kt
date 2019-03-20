package controlcenter.service.impl.filter

import controlcenter.service.CommandTextFilter
import controlcenter.service.DelegatableService
import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Service
@Primary
class PrimaryCommandTextFilter(allCommandTextFilters: List<CommandTextFilter>) : DelegatableService<CommandTextFilter>(allCommandTextFilters), CommandTextFilter {
    private val log = LogManager.getLogger(this.javaClass)

    override fun filterText(commandText: String): String {
        log.info("Apply filters for text=$commandText")

        var resultFilteredText = commandText
        for (commandTextFilter in otherServices) {
            val filterName = commandTextFilter.javaClass.simpleName
            log.info("Use text filter=$filterName for text=$commandText")

            resultFilteredText = commandTextFilter.filterText(resultFilteredText)

            if (resultFilteredText.isEmpty()) {
                log.info("Got empty text after text filter=$filterName")
                break
            }
        }
        return resultFilteredText
    }
}
