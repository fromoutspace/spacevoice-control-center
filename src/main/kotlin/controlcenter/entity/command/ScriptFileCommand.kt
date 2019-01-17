package controlcenter.entity.command

import controlcenter.enumeration.CommandExecType

abstract class ScriptFileCommand(val filePath: String, execType: CommandExecType) : Command(execType)
