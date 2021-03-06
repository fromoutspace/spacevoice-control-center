package controlcenter.service.impl

import controlcenter.domain.CommandHistoryDomain
import controlcenter.entity.command.Command
import controlcenter.repository.CommandHistoryRepository
import controlcenter.service.CommandHistoryService
import controlcenter.service.CommandMapper
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommandHistoryServiceImpl(private val commandHistoryRepository: CommandHistoryRepository,
                                private val commandMapper: CommandMapper) : CommandHistoryService {
    private val log = LogManager.getLogger(this.javaClass)

    @Transactional
    override fun saveCommand(commandText: List<String>, command: Command) {
        log.debug("Command with text=$commandText was saved in history")
        val historyDomain = CommandHistoryDomain(commandText, command.id)
        commandHistoryRepository.save(historyDomain)
    }

    override fun getCommandEntity(commandHistoryDomain: CommandHistoryDomain) =
            commandMapper.toCommandEntity(commandHistoryDomain.command)

    override fun findCommandByText(commandText: List<String>) =
            commandHistoryRepository.findFirstByCommandTextOrderByCreatedAtDesc(commandText.joinToString(" "))
}