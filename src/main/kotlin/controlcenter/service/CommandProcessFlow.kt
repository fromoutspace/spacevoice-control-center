package controlcenter.service

import controlcenter.service.impl.factory.HistoryCommandFactory
import org.apache.logging.log4j.LogManager
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class CommandProcessFlow(
        private val commandFactory: CommandFactory,
        private val commandRunner: CommandRunner,
        private val historyCommandFactory: HistoryCommandFactory,
        private val alternativeOptionsProvider: AlternativeOptionsProvider
) {
    private val log = LogManager.getLogger(this.javaClass)

    @Async
    fun processCommand(commandText: String) {
        log.info("Start processing command '$commandText'")

        log.info("Searching '$commandText' in history")
        val commandFromHistory = historyCommandFactory.tryToCreateCommand(commandText)
        if (commandFromHistory != null)
            return commandRunner.runCommand(commandFromHistory)

        log.info("Look up for alternative options '$commandText' ")
        val allCommandTextOptions = alternativeOptionsProvider.getAlternativeOptions(commandText)

        log.info("Trying to create command for'$commandText' with alternatives $allCommandTextOptions")
        for (alternativeCommandText in allCommandTextOptions) {
            val command = commandFactory.tryToCreateCommand(alternativeCommandText)
            if (command != null) commandRunner.runCommand(command)
        }
    }
}