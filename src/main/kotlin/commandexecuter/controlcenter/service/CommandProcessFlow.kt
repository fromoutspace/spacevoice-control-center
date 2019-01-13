package commandexecuter.controlcenter.service

import commandexecuter.controlcenter.service.impl.factory.HistoryCommandFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class CommandProcessFlow(
        private val commandFactory: CommandFactory,
        private val commandRunner: CommandRunner,
        private val historyCommandFactory: HistoryCommandFactory,
        private val alternativeProvider: AlternativeProvider
) {
    @Async
    fun processCommand(commandText: String) {
        val commandFromHistory = historyCommandFactory.tryToCreateCommand(commandText)
        if (commandFromHistory != null)
            return commandRunner.runCommand(commandFromHistory)


        val allCommandTextAlternatives = alternativeProvider.getAlternativeVariants(commandText)
        if (allCommandTextAlternatives.isEmpty()) return

        for (alternativeCommandText in allCommandTextAlternatives) {
            val command = commandFactory.tryToCreateCommand(alternativeCommandText)
            if (command != null) commandRunner.runCommand(command)
        }
    }
}