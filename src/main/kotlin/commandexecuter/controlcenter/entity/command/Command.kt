package commandexecuter.controlcenter.entity.command

import commandexecuter.controlcenter.enumeration.CommandExecType

abstract class Command(val execType: CommandExecType)

fun emptyCommand() = object : Command(execType = CommandExecType.NONE) {}