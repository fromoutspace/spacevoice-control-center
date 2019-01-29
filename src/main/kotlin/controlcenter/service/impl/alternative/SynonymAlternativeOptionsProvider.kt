package controlcenter.service.impl.alternative

import controlcenter.service.AlternativeOptionsProvider
import org.springframework.stereotype.Service

@Service
class SynonymAlternativeOptionsProvider : AlternativeOptionsProvider {
    override fun getAlternativeOptions(commandText: String): Set<String> {
        return emptySet()
    }
}