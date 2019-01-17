package controlcenter.entity.command

import controlcenter.domain.CommandDomain
import controlcenter.enumeration.CommandExecType

class BatchFileCommand(filePath: String) : ScriptFileCommand(filePath, CommandExecType.BATCH_FILE) {
    constructor(entity: CommandDomain) : this(entity.path)
}