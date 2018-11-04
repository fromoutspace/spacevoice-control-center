package commandexecuter.controlcenter.service

import commandexecuter.controlcenter.domain.CommandDomain
import commandexecuter.controlcenter.enumeration.CommandParseType

interface CommandService {
    fun getAllByParseType(commandType: CommandParseType): List<CommandDomain>
}
