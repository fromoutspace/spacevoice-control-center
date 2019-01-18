package controlcenter.entity.command

import controlcenter.domain.CommandDomain
import controlcenter.enumeration.CommandExecType

class ShellCommand(commandDomain: CommandDomain) : Command {
    override val id: Long? = commandDomain.id
    val shellCommand: String = commandDomain.content
    override val execType = CommandExecType.SHELL_COMMAND
}