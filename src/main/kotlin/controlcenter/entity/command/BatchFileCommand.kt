package controlcenter.entity.command

import controlcenter.domain.CommandDomain
import controlcenter.enumeration.CommandExecType

class BatchFileCommand(commandDomain: CommandDomain) :
        ScriptFileCommand(commandDomain.id, commandDomain.content, CommandExecType.BATCH_FILE)