package commandexecuter.controlcenter.repository

import commandexecuter.controlcenter.domain.SettingDomain
import org.springframework.data.repository.CrudRepository

interface SettingRepository : CrudRepository<SettingDomain, String>
