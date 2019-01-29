package controlcenter.domain

import java.time.Instant
import java.time.Instant.now
import javax.persistence.*

@Entity(name = "command_history")
class CommandHistoryDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var commandText: String = ""
    var updatedAt: Instant = Instant.MIN
    @ManyToOne(fetch = FetchType.EAGER)
    var command: CommandDomain = emptyCommandDomain()

    constructor(commandText: List<String>, commandDomain: CommandDomain) {
        this.commandText = commandText.joinToString(" ")
        this.command = commandDomain
        this.updatedAt = Instant.now()
    }

    constructor(commandText: List<String>, commandDomainId: Long?) :
            this(commandText, CommandDomain().apply { id = commandDomainId })

    fun withUpdatedDate() = this.apply { updatedAt = now() }
}