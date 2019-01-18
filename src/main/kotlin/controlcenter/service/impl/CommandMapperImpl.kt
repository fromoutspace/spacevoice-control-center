package controlcenter.service.impl

import controlcenter.domain.CommandDomain
import controlcenter.entity.command.BatchFileCommand
import controlcenter.entity.command.Command
import controlcenter.entity.command.ShellCommand
import controlcenter.enumeration.CommandExecType.BATCH_FILE
import controlcenter.enumeration.CommandExecType.SHELL_COMMAND
import controlcenter.service.CommandMapper
import controlcenter.web.errors.UnknownSavedCommandException
import org.springframework.stereotype.Service

@Service
class CommandMapperImpl : CommandMapper {
    override fun toCommandEntity(commandDomain: CommandDomain): Command {
        return when {
            commandDomain.execType == BATCH_FILE -> BatchFileCommand(commandDomain)
            commandDomain.execType == SHELL_COMMAND -> ShellCommand(commandDomain)
            else -> throw UnknownSavedCommandException(commandDomain)
        }
    }
}