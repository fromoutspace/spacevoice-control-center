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
class RegexMatchCommandFactory(private val commandService: CommandService,
                               private val commandMapper: CommandMapper) : CommandFactory {
    private val log = LogManager.getLogger(this.javaClass)

    override fun tryToCreateCommand(commandText: List<String>): Command? {
        val savedCommands = commandService.findAllByParseType(CommandParseType.REGEX_MATCH)

        return savedCommands
                .firstOrNull { savedCommand -> isMatchByRegexPattern(savedCommand, commandText) }
                ?.also { savedCommand -> log.debug("Match Command '$commandText' with regex '${savedCommand.matchValue}'") }
                ?.let { commandMapper.toCommandEntity(it) }
    }

    private fun isMatchByRegexPattern(savedCommand: CommandDomain, commandText: List<String>): Boolean {
        val regexPattern = savedCommand.matchValue
        val commandTextString = commandText.joinToString(" ")
        return commandTextString.matches(Regex(regexPattern))
    }
}
