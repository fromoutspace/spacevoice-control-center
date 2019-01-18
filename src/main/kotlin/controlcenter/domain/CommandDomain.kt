package controlcenter.domain

import controlcenter.enumeration.CommandExecType
import controlcenter.enumeration.CommandParseType
import javax.persistence.*

@Entity(name = "command")
class CommandDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var matchValue: String = ""
    @Enumerated(EnumType.STRING)
    var parseType: CommandParseType = CommandParseType.NONE
    @Enumerated(EnumType.STRING)
    var execType: CommandExecType = CommandExecType.NONE
    var content: String = ""
}

fun emptyCommandDomain() = CommandDomain().apply {
    id = 0
    matchValue = "empty command"
    parseType = CommandParseType.NONE
    execType = CommandExecType.NONE
    content = ""
}
