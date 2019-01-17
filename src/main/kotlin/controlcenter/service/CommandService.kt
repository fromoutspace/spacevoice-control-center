package controlcenter.service

import controlcenter.domain.CommandDomain
import controlcenter.enumeration.CommandParseType

interface CommandService {
    fun findAllByParseType(commandType: CommandParseType): List<CommandDomain>
}
