package controlcenter.entity.command

import controlcenter.domain.CommandDomain
import controlcenter.enumeration.CommandExecType

class AppCommand(commandDomain: CommandDomain) : Command {
    override val id: Long? = commandDomain.id
    val applicationPath: String = commandDomain.content
    override val execType = CommandExecType.APP
}