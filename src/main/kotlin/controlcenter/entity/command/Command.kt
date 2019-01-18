package controlcenter.entity.command

import controlcenter.enumeration.CommandExecType

interface Command {
    val id: Long?
    val execType: CommandExecType
}