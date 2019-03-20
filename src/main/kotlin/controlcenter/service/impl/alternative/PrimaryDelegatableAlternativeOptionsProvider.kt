package controlcenter.service.impl.alternative

import controlcenter.service.AlternativeOptionsProvider
import controlcenter.service.DelegatableService
import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Primary
@Service
class PrimaryDelegatableAlternativeOptionsProvider(allOptionsProviders: List<AlternativeOptionsProvider>) : DelegatableService<AlternativeOptionsProvider>(allOptionsProviders), AlternativeOptionsProvider {
    private val log = LogManager.getLogger(this.javaClass)

    override fun getAlternativeOptions(commandText: List<String>): Set<List<String>> {
        val allAlternatives = mutableSetOf(commandText)

        for (provider in otherServices) {
            val providerName = provider.javaClass.simpleName
            log.info("Use alternative provider=$providerName for command text '$commandText'")

            val alternatives = provider.getAlternativeOptions(commandText)

            log.debug("Got ${alternatives.size} alternatives using provider=$providerName for text=$commandText, alternatives=$alternatives")
            allAlternatives += alternatives
        }

        return allAlternatives
    }
}