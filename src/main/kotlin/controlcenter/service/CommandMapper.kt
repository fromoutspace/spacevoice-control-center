package controlcenter.service

import controlcenter.domain.CommandDomain
import controlcenter.entity.command.Command

interface CommandMapper {
    fun toCommandEntity(commandDomain: CommandDomain): Command
}