package controlcenter.service

import controlcenter.entity.command.Command

interface CommandFactory : Prioritized {
    fun tryToCreateCommand(commandText: List<String>): Command?
}