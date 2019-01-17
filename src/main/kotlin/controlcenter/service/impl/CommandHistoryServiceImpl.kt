package controlcenter.service.impl

import controlcenter.domain.CommandHistoryDomain
import controlcenter.entity.command.Command
import controlcenter.repository.CommandHistoryRepository
import controlcenter.service.CommandHistoryService
import controlcenter.service.mapper.CommandMapper
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommandHistoryServiceImpl(private val commandHistoryRepository: CommandHistoryRepository,
                                private val commandMapper: CommandMapper) : CommandHistoryService {
    private val log = LogManager.getLogger(this.javaClass)

    @Transactional
    override fun saveCommand(commandText: String, command: Command) {
        val existedHistoryDomain = commandHistoryRepository.findFirstByCommandText(commandText)
        if (existedHistoryDomain != null) {
            log.info("Update command '$commandText' in history")
            commandHistoryRepository.save(existedHistoryDomain.withUpdatedDate())
        } else {
            log.info("Save command '$commandText' to history")
            val historyDomain = commandMapper.toHistoryDomain(commandText, command)
            commandHistoryRepository.save(historyDomain)
        }
    }

    override fun getCommand(commandHistoryDomain: CommandHistoryDomain): Command {
        val commandEntity = commandHistoryDomain.command
        return commandMapper.toCommandEntity(commandEntity)
    }

    override fun findCommandByText(commandText: String) =
            commandHistoryRepository.findFirstByCommandText(commandText)?.also {
                log.info("Got command '$commandText' from history")
            }
}