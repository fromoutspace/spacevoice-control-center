package controlcenter.service.impl.runner

import controlcenter.entity.command.BatchFileCommand
import controlcenter.entity.command.Command
import controlcenter.service.CommandRunner
import org.jboss.logging.Logger
import org.springframework.stereotype.Service

@Service
class OperationSystemCommandRunner : CommandRunner {
    private val log = Logger.getLogger(this.javaClass)

    override fun runCommand(command: Command) {
        if (command is BatchFileCommand) {
            val filePath = "\"${command.filePath}\""
            log.info("Running Command as BATCH file $filePath using ${this.javaClass.simpleName}")
            Runtime.getRuntime().exec(filePath)
        }
    }

    override fun canRunCommand(command: Command) = command is BatchFileCommand
}
