package commandexecuter.controlcenter.service.impl.runner

import commandexecuter.controlcenter.entity.command.Command
import commandexecuter.controlcenter.service.CommandRunner
import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Service
@Primary
class PrimaryDelegatableCommandRunner(allCommandRunners: List<CommandRunner>) : CommandRunner {
    private val log = LogManager.getLogger(this.javaClass)

    private val otherCommandRunners = allCommandRunners
            .filter { it != this }

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
            otherCommandRunners.firstOrNull { it.canRunCommand(command) }
}
