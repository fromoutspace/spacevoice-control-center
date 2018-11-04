package commandexecuter.controlcenter.service

import commandexecuter.controlcenter.entity.command.Command

interface CommandFactory {
    val executionPriority get() = 0

    fun tryToCreateCommand(commandText: String): Command?
}