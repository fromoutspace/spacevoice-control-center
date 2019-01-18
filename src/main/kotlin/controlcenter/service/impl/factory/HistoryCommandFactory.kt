package controlcenter.service.impl.factory

import controlcenter.entity.command.Command
import controlcenter.service.CommandFactory
import controlcenter.service.CommandHistoryService
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service

@Service
class HistoryCommandFactory(val commandHistoryService: CommandHistoryService) : CommandFactory {
    private val log = LogManager.getLogger(this.javaClass)

    override val executionPriority = 10

    override fun tryToCreateCommand(commandText: String): Command? {
        val commandFromHistory = commandHistoryService.findCommandByText(commandText)
        return if (commandFromHistory != null) {
            log.info("Found ${commandFromHistory.command.execType} command in history for text '$commandText'")
            commandHistoryService.getCommandEntity(commandFromHistory)
        } else {
            log.info("No records in history for command '$commandText'")
            null
        }
    }
}
