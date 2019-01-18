package controlcenter.entity.command

import controlcenter.enumeration.CommandExecType

abstract class ScriptFileCommand(override val id: Long?,
                                 val filePath: String,
                                 override val execType: CommandExecType) : Command
