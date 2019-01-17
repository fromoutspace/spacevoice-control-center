package controlcenter.service.impl

import controlcenter.domain.CommandDomain
import controlcenter.enumeration.CommandParseType
import controlcenter.repository.CommandRepository
import controlcenter.service.CommandService
import org.springframework.stereotype.Service

@Service
class CommandServiceImpl(private val commandRepository: CommandRepository) : CommandService {
    override fun getAllByParseType(parseType: CommandParseType): List<CommandDomain> {
        return commandRepository.findAllByParseType(parseType)
    }
}