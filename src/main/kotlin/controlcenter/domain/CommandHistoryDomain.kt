package controlcenter.domain

import java.time.Instant
import javax.persistence.*

@Entity(name = "command_history")
class CommandHistoryDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var commandText: String = ""
    var createdAt: Instant = Instant.MIN
    @ManyToOne(fetch = FetchType.EAGER)
    var command: CommandDomain = emptyCommandDomain()

    constructor(commandText: List<String>, commandDomain: CommandDomain) {
        this.commandText = commandText.joinToString(" ")
        this.command = commandDomain
        this.createdAt = Instant.now()
    }

    constructor(commandText: List<String>, commandDomainId: Long?) :
            this(commandText, CommandDomain().apply { id = commandDomainId })
}