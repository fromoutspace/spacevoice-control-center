package controlcenter.service.impl.runner

import controlcenter.entity.command.AppCommand
import controlcenter.entity.command.Command
import controlcenter.service.CommandRunner
import org.jboss.logging.Logger
import org.springframework.stereotype.Service
import java.nio.file.Paths


@Service
class AppCommandRunner : CommandRunner {
    private val log = Logger.getLogger(this.javaClass)

    override fun runCommand(command: Command) {
        if (command is AppCommand) {
            log.info("Running Application Command '${command.applicationPath}' using ${this.javaClass.simpleName}")
            val path = Paths.get(command.applicationPath)
            Runtime.getRuntime().exec(path.toString(), null, path.parent.toFile())
        }
    }

    override fun canRunCommand(command: Command) = command is AppCommand
}
