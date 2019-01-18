package controlcenter.service.impl.runner

import controlcenter.entity.command.Command
import controlcenter.entity.command.ShellCommand
import controlcenter.service.CommandRunner
import org.jboss.logging.Logger
import org.springframework.stereotype.Service

@Service
class ShellCommandCommandRunner : CommandRunner {
    private val log = Logger.getLogger(this.javaClass)

    override fun runCommand(command: Command) {
        if (command is ShellCommand) {
            log.info("Running shell Command '${command.shellCommand}' using ${this.javaClass.simpleName}")
            Runtime.getRuntime().exec("\"${command.shellCommand}\"")
        }
    }

    override fun canRunCommand(command: Command) = command is ShellCommand
}
