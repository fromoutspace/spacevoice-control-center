package commandexecuter.controlcenter.service.impl.factory

import commandexecuter.controlcenter.domain.CommandDomain
import commandexecuter.controlcenter.entity.command.Command
import commandexecuter.controlcenter.enumeration.CommandParseType
import commandexecuter.controlcenter.service.CommandFactory
import commandexecuter.controlcenter.service.CommandService
import commandexecuter.controlcenter.service.mapper.CommandMapper
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service

@Service
class DirectMatchCommandFactory(private val commandService: CommandService,
                                private val commandMapper: CommandMapper) : CommandFactory {
    private val log = LogManager.getLogger(this.javaClass)

    override fun tryToCreateCommand(commandText: String): Command? {
        val existedCommands = commandService.getAllByParseType(CommandParseType.DIRECT_MATCH)

        return existedCommands
                .firstOrNull { existedCommand -> isCommandTextEquals(existedCommand, commandText) }
                ?.also { existedCommand -> log.debug("Match Command '$commandText' by direct match '${existedCommand.matchValue}'") }
                ?.let { commandMapper.toCommandEntity(it) }
    }

    private fun isCommandTextEquals(existedCommand: CommandDomain, commandText: String): Boolean {
        val commandTextFromTable = existedCommand.matchValue
        return commandTextFromTable.toLowerCase() == commandText.toLowerCase()
    }
}