package commandexecuter.controlcenter.entity.command

import commandexecuter.controlcenter.enumeration.CommandExecType

abstract class ScriptFileCommand(val filePath: String, execType: CommandExecType) : Command(execType)
