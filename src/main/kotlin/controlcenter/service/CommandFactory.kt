package controlcenter.service

import controlcenter.entity.command.Command

interface CommandFactory {
    val executionPriority get() = 0

    fun tryToCreateCommand(commandText: String): Command?
}