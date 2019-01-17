package controlcenter.entity.command

import controlcenter.enumeration.CommandExecType

abstract class Command(val execType: CommandExecType)

fun emptyCommand() = object : Command(execType = CommandExecType.NONE) {}