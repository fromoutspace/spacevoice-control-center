package controlcenter.service.impl.alternative

import controlcenter.service.AlternativeProvider
import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Primary
@Service
class PrimaryDelegatableAlternativeProvider(allProviders: List<AlternativeProvider>) : AlternativeProvider {
    private val log = LogManager.getLogger(this.javaClass)

    private val otherProviders = allProviders
            .filter { it != this }

    override fun getAlternativeVariants(commandText: String): Set<String> {
        val allAlternatives = mutableSetOf(commandText)

        for (provider in otherProviders) {
            log.info("Use alternative provider '${provider.javaClass.simpleName}' for command text '$commandText'")

            val alternatives = provider.getAlternativeVariants(commandText)

            if (allAlternatives.isEmpty() && provider.alternativesRequiredNotBeEmpty)
                return emptySet()

            log.debug("Got alternatives for text '$commandText' $alternatives")
            allAlternatives += alternatives
        }

        return allAlternatives
    }
}