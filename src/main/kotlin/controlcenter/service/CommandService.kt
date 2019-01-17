package controlcenter.service

import controlcenter.domain.CommandDomain
import controlcenter.enumeration.CommandParseType

interface CommandService {
    fun getAllByParseType(commandType: CommandParseType): List<CommandDomain>
}
