package commandexecuter.controlcenter.service

import commandexecuter.controlcenter.domain.CommandHistoryDomain
import commandexecuter.controlcenter.entity.command.Command

interface CommandHistoryService {
    fun findCommandByText(commandText: String): CommandHistoryDomain?
    fun getCommand(commandHistoryDomain: CommandHistoryDomain): Command?
    fun saveCommand(commandText: String, command: Command)
}