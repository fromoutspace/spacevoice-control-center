package commandexecuter.controlcenter.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "setting")
class SettingDomain {
    @Id
    var key = ""
    var value = ""
}