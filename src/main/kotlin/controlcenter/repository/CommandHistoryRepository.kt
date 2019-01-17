package controlcenter.repository

import controlcenter.domain.CommandHistoryDomain
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CommandHistoryRepository : CrudRepository<CommandHistoryDomain, Long> {
    fun findFirstByCommandText(commandText: String): CommandHistoryDomain?
}