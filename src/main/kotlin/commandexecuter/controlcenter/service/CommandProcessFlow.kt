package commandexecuter.controlcenter.service

import commandexecuter.controlcenter.service.impl.factory.HistoryCommandFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class CommandProcessFlow(
        private val commandFactory: CommandFactory,
        private val commandRunner: CommandRunner,
        private val historyCommandFactory: HistoryCommandFactory,
        private val textFilter: TextFilter
) {
    @Async
    fun processCommand(commandText: String) {
        val commandFromHistory = historyCommandFactory.tryToCreateCommand(commandText)
        if (commandFromHistory != null)
            return commandRunner.runCommand(commandFromHistory)


        val filteredCommandText = textFilter.filter(commandText)
        if (filteredCommandText.isEmpty()) return

        val command = commandFactory.tryToCreateCommand(filteredCommandText)
        if (command != null) commandRunner.runCommand(command)
    }
}