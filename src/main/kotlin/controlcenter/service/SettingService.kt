package controlcenter.service

interface SettingService {
    fun get(key: String): String
    fun set(key: String, value: String)
    fun isTrue(key: String): Boolean
}