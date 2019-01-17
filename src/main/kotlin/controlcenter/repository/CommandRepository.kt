package controlcenter.repository

import controlcenter.domain.CommandDomain
import controlcenter.enumeration.CommandParseType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CommandRepository : CrudRepository<CommandDomain, Long> {
    fun findAllByParseType(parseType: CommandParseType): List<CommandDomain>
}
