package controlcenter.service.impl.factory

import controlcenter.entity.command.Command
import controlcenter.service.AlternativeOptionsProvider
import controlcenter.service.CommandFactory
import controlcenter.service.CommandHistoryService
import controlcenter.service.DelegatableService
import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Service
@Primary
class PrimaryDelegatableCommandFactory(allCommandFactories: List<CommandFactory>,
                                       private val historyService: CommandHistoryService,
                                       private val alternativeOptionsProvider: AlternativeOptionsProvider) : DelegatableService<CommandFactory>(allCommandFactories), CommandFactory {
    private val log = LogManager.getLogger(this.javaClass)


    override fun tryToCreateCommand(commandText: List<String>): Command? {
        log.info("Look up for alternative options=$commandText")
        val allCommandTextOptions = alternativeOptionsProvider.getAlternativeOptions(commandText)

        log.info("Trying to create Command  with ${allCommandTextOptions.size} options=$allCommandTextOptions for text=$commandText")
        for (commandTextOption in allCommandTextOptions) {
            log.info("Trying to create Command using option=$commandTextOption")
            for (commandFactory in otherServices) {
                val factoryName = commandFactory.javaClass.simpleName

                log.info("Try to create Command using commandFactory=$factoryName")
                val command = commandFactory.tryToCreateCommand(commandTextOption)

                if (command != null) {
                    log.info("Created Command of execType=${command.execType} using commandFactory=$factoryName")

                    log.info("Saving text=$commandText to history")
                    historyService.saveCommand(commandText, command)

                    return command
                }
            }
        }

        log.info("There is no factory that could create Command from text=$commandText")
        return null
    }
}
