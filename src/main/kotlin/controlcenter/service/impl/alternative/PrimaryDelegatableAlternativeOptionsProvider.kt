package controlcenter.service.impl.alternative

import controlcenter.service.AlternativeOptionsProvider
import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Primary
@Service
class PrimaryDelegatableAlternativeOptionsProvider(allOptionsProviders: List<AlternativeOptionsProvider>) : AlternativeOptionsProvider {
    private val log = LogManager.getLogger(this.javaClass)

    private val otherProviders = allOptionsProviders
            .filter { it != this }

    override fun getAlternativeOptions(commandText: String): Set<String> {
        val allAlternatives = mutableSetOf(commandText)

        for (provider in otherProviders) {
            log.info("Using alternative provider '${provider.javaClass.simpleName}' for command text '$commandText'")

            val alternatives = provider.getAlternativeOptions(commandText)

            log.debug("Got alternatives for text '$commandText' $alternatives")
            allAlternatives += alternatives
        }

        return allAlternatives
    }
}