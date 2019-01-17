package controlcenter.service.impl.factory

import controlcenter.domain.CommandDomain
import controlcenter.entity.command.Command
import controlcenter.enumeration.CommandParseType
import controlcenter.service.CommandFactory
import controlcenter.service.CommandService
import controlcenter.service.mapper.CommandMapper
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service

@Service
class RegexMatchCommandFactory(private val commandService: CommandService,
                               private val commandMapper: CommandMapper) : CommandFactory {
    private val log = LogManager.getLogger(this.javaClass)

    override fun tryToCreateCommand(commandText: String): Command? {
        val existedCommands = commandService.findAllByParseType(CommandParseType.REGEX_MATCH)

        return existedCommands
                .firstOrNull { existedCommand -> isMatchByRegexPattern(existedCommand, commandText) }
                ?.also { existedCommand -> log.debug("Match Command '$commandText' with regex '${existedCommand.matchValue}'") }
                ?.let { commandMapper.toCommandEntity(it) }
    }

    private fun isMatchByRegexPattern(existedCommand: CommandDomain, commandText: String): Boolean {
        val regexPattern = existedCommand.matchValue
        return commandText.matches(Regex(regexPattern))
    }
}
