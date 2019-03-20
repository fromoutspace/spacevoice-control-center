package controlcenter.service.impl.runner

import controlcenter.entity.command.Command
import controlcenter.service.CommandRunner
import controlcenter.service.DelegatableService
import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Service
@Primary
class PrimaryDelegatableCommandRunner(allCommandRunners: List<CommandRunner>) : DelegatableService<CommandRunner>(allCommandRunners), CommandRunner {
    private val log = LogManager.getLogger(this.javaClass)

    override fun canRunCommand(command: Command) = true

    override fun runCommand(command: Command) {
        val commandRunner = findFirstCommandRunnerThatCanRunCommand(command)

        if (commandRunner == null) {
            log.info("Can't find command runner for command execType '${command.execType}'")
            return
        }

        val runnerName = commandRunner.javaClass.simpleName
        log.info("Used runner '$runnerName' for command with exec type '${command.execType}'")
        commandRunner.runCommand(command)
    }

    private fun findFirstCommandRunnerThatCanRunCommand(command: Command) =
            otherServices.firstOrNull { it.canRunCommand(command) }
}
