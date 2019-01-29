package controlcenter.service.impl.factory

import controlcenter.domain.CommandDomain
import controlcenter.entity.command.Command
import controlcenter.enumeration.CommandParseType
import controlcenter.service.CommandFactory
import controlcenter.service.CommandMapper
import controlcenter.service.CommandService
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service

@Service
class DirectMatchCommandFactory(private val commandService: CommandService,
                                private val commandMapper: CommandMapper) : CommandFactory {
    private val log = LogManager.getLogger(this.javaClass)

    override fun tryToCreateCommand(commandText: List<String>): Command? {
        val savedCommands = commandService.findAllByParseType(CommandParseType.DIRECT_MATCH)

        return savedCommands
                .firstOrNull { savedCommand -> isCommandTextEquals(savedCommand, commandText) }
                ?.also { savedCommand -> log.debug("Match Command '$commandText' by direct match '${savedCommand.matchValue}'") }
                ?.let { commandMapper.toCommandEntity(it) }
    }

    private fun isCommandTextEquals(savedCommand: CommandDomain, commandText: List<String>): Boolean {
        val savedCommandText = savedCommand.matchValue
        val commandTextString = commandText.joinToString(" ")
        return savedCommandText.toLowerCase() == commandTextString.toLowerCase()
    }
}