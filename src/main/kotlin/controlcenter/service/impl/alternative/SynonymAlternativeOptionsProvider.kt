package controlcenter.service.impl.alternative

import controlcenter.service.AlternativeOptionsProvider
import org.springframework.stereotype.Service

@Service
class SynonymAlternativeOptionsProvider : AlternativeOptionsProvider {
    override fun getAlternativeOptions(commandText: List<String>): Set<List<String>> {
        return emptySet()
    }
}