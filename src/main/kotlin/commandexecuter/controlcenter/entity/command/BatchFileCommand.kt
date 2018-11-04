package commandexecuter.controlcenter.entity.command

import commandexecuter.controlcenter.domain.CommandDomain
import commandexecuter.controlcenter.enumeration.CommandExecType

class BatchFileCommand(filePath: String) : ScriptFileCommand(filePath, CommandExecType.BATCH_FILE) {
    constructor(entity: CommandDomain) : this(entity.path)
}