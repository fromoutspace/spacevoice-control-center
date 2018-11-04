package commandexecuter.controlcenter.service.impl.factory

import commandexecuter.controlcenter.entity.command.Command
import commandexecuter.controlcenter.service.CommandFactory
import commandexecuter.controlcenter.service.CommandHistoryService
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service

@Service
class HistoryCommandFactory(val commandHistoryService: CommandHistoryService) : CommandFactory {
    private val log = LogManager.getLogger(this.javaClass)

    override val executionPriority = 10

    override fun tryToCreateCommand(commandText: String): Command? {
        val commandFromHistory = commandHistoryService.findCommandByText(commandText)

        return if (commandFromHistory != null) {
            log.info("Found ${commandFromHistory.command.execType} command in history")
            commandHistoryService.getCommand(commandFromHistory)
        } else {
            log.info("No records in history for command '$commandText'")
            null
        }
    }
}
