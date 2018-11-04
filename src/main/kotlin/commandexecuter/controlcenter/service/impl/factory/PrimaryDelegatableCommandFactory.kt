package commandexecuter.controlcenter.service.impl.factory

import commandexecuter.controlcenter.entity.command.Command
import commandexecuter.controlcenter.service.CommandFactory
import commandexecuter.controlcenter.service.CommandHistoryService
import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Service
@Primary
class PrimaryDelegatableCommandFactory(allCommandFactories: List<CommandFactory>,
                                       val historyService: CommandHistoryService) : CommandFactory {
    private val log = LogManager.getLogger(this.javaClass)

    private val otherCommandFactories = allCommandFactories
            .filter { it != this }
            .sortedByDescending { it.executionPriority }

    override fun tryToCreateCommand(commandText: String): Command? {
        for (commandFactory in otherCommandFactories) {
            val command = tryToCreateCommandAndSaveToHistory(commandFactory, commandText)
            if (command != null) return command
        }

        log.info("Can't find any factory to create Command for text '$commandText'")
        return null
    }

    private fun tryToCreateCommandAndSaveToHistory(commandFactory: CommandFactory, commandText: String): Command? {
        val factoryName = commandFactory.javaClass.simpleName
        log.debug("Try to create Command using commandFactory $factoryName")

        val command = commandFactory.tryToCreateCommand(commandText)

        if (command != null) {
            log.info("Created Command of execType '${command.execType}' using commandFactory '$factoryName'")
            historyService.saveCommand(commandText, command)
        }

        return command
    }
}
