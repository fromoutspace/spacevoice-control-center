package commandexecuter.controlcenter.domain

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
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var command: CommandDomain = emptyCommandDomain()

    fun withUpdatedDate() = this.apply { updatedAt = now() }
}