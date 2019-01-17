package controlcenter.service

import controlcenter.entity.command.Command

interface CommandRunner {
    fun runCommand(command: Command)
    fun canRunCommand(command: Command): Boolean
}
