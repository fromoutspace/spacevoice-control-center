package commandexecuter.controlcenter.service

import commandexecuter.controlcenter.entity.command.Command

interface CommandRunner {
    fun runCommand(command: Command)
    fun canRunCommand(command: Command): Boolean
}
