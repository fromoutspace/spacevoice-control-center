package controlcenter.domain

import controlcenter.enumeration.CommandExecType
import controlcenter.enumeration.CommandParseType
import java.time.Instant
import java.time.Instant.now
import javax.persistence.*

@Entity(name = "command")
class CommandDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var title: String = ""
    var matchValue: String = ""
    @Enumerated(EnumType.STRING)
    var parseType: CommandParseType = CommandParseType.NONE
    @Enumerated(EnumType.STRING)
    var execType: CommandExecType = CommandExecType.NONE
    var path: String = ""
    var createdAt: Instant = Instant.MIN
}

fun emptyCommandDomain() = CommandDomain().apply {
    id = 0
    title = "default empty command"
    matchValue = "empty command"
    parseType = CommandParseType.NONE
    execType = CommandExecType.NONE
    path = ""
    createdAt = now()
}
