package controlcenter.repository

import controlcenter.domain.SettingDomain
import org.springframework.data.repository.CrudRepository

interface SettingRepository : CrudRepository<SettingDomain, String>
