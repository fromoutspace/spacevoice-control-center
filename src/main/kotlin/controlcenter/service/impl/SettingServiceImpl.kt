package controlcenter.service.impl

import controlcenter.domain.SettingDomain
import controlcenter.repository.SettingRepository
import controlcenter.service.SettingService
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

    override fun isTrue(key: String) = get(key) == "true"
}