package commandexecuter.controlcenter.service.mapper

import commandexecuter.controlcenter.domain.CommandDomain
import commandexecuter.controlcenter.domain.CommandHistoryDomain
import commandexecuter.controlcenter.entity.command.BatchFileCommand
import commandexecuter.controlcenter.entity.command.Command
import commandexecuter.controlcenter.entity.command.ScriptFileCommand
import commandexecuter.controlcenter.enumeration.CommandExecType.BATCH_FILE
import commandexecuter.controlcenter.enumeration.CommandParseType
import commandexecuter.controlcenter.web.errors.UnknownSavedCommandException
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CommandMapper {
    fun toCommandEntity(entity: CommandDomain): Command {
        if (entity.execType == BATCH_FILE)
            return BatchFileCommand(entity)
        throw UnknownSavedCommandException(entity)
    }

    fun toCommandDomain(commandText: String, command: Command): CommandDomain {
        return CommandDomain().apply {
            this.execType = command.execType
            this.parseType = CommandParseType.DIRECT_MATCH
            this.path = (command as? ScriptFileCommand)?.filePath ?: ""
            this.title = commandText
            this.createdAt = Instant.now()
            this.matchValue = commandText
        }
    }

    fun toHistoryDomain(commandText: String, command: Command): CommandHistoryDomain {
        val commandDomain = toCommandDomain(commandText, command)
        return CommandHistoryDomain().apply {
            this.commandText = commandText
            this.command = commandDomain
            this.updatedAt = Instant.now()
        }
    }
}
