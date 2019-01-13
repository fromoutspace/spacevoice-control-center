package commandexecuter.controlcenter.service.impl.filter

import commandexecuter.controlcenter.service.TextFilter
import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Primary
@Service
class PrimaryDelegatableTextFilter(allFilters: List<TextFilter>) : TextFilter {
    private val log = LogManager.getLogger(this.javaClass)

    private val otherFilters = allFilters
            .filter { it != this }

    override fun filter(text: String): String {
        var filteredText = text

        for (filter in otherFilters) {
            filteredText = filter.filter(filteredText)
                    .also {
                        log.info("Used filter '${filter.javaClass.simpleName}' for text '$filteredText' with result '$it'")
                    }

            if (filteredText == "") return ""
        }

        return filteredText
    }
}