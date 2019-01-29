package controlcenter.service

import controlcenter.domain.CommandDomain
import controlcenter.enumeration.CommandParseType

interface CommandService {
    fun findAllByParseType(parseType: CommandParseType): List<CommandDomain>
}
