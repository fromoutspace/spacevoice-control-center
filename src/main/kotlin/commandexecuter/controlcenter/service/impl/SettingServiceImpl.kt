package commandexecuter.controlcenter.service.impl

import commandexecuter.controlcenter.domain.SettingDomain
import commandexecuter.controlcenter.repository.SettingRepository
import commandexecuter.controlcenter.service.SettingService
import org.springframework.stereotype.Service

@Service
class SettingServiceImpl(private val settingRepository: SettingRepository) : SettingService {
    override fun get(key: String): String = settingRepository.findById(key)
            .map { it.value }
            .orElse("")

    override fun set(key: String, value: String) {
        val updatedSetting = SettingDomain().apply {
            this.key = key
            this.value = value
        }
        settingRepository.save(updatedSetting)
    }
}