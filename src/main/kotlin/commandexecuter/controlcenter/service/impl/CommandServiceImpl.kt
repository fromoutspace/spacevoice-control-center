package commandexecuter.controlcenter.service.impl

import commandexecuter.controlcenter.domain.CommandDomain
import commandexecuter.controlcenter.enumeration.CommandParseType
import commandexecuter.controlcenter.repository.CommandRepository
import commandexecuter.controlcenter.service.CommandService
import org.springframework.stereotype.Service

@Service
class CommandServiceImpl(private val commandRepository: CommandRepository) : CommandService {
    override fun getAllByParseType(parseType: CommandParseType): List<CommandDomain> {
        return commandRepository.findAllByParseType(parseType)
    }
}