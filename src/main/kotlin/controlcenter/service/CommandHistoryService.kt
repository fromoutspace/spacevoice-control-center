package controlcenter.service

import controlcenter.domain.CommandHistoryDomain
import controlcenter.entity.command.Command

interface CommandHistoryService {
    fun findCommandByText(commandText: String): CommandHistoryDomain?
    fun getCommandEntity(commandHistoryDomain: CommandHistoryDomain): Command?
    fun saveCommand(commandText: String, command: Command)
}