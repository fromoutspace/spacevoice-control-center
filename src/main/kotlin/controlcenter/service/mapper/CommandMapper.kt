package controlcenter.service.mapper

import controlcenter.domain.CommandDomain
import controlcenter.domain.CommandHistoryDomain
import controlcenter.entity.command.BatchFileCommand
import controlcenter.entity.command.Command
import controlcenter.entity.command.ScriptFileCommand
import controlcenter.enumeration.CommandExecType.BATCH_FILE
import controlcenter.enumeration.CommandParseType
import controlcenter.web.errors.UnknownSavedCommandException
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
