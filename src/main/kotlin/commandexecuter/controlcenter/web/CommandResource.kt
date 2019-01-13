package commandexecuter.controlcenter.web

import com.fasterxml.jackson.databind.JsonNode
import commandexecuter.controlcenter.service.CommandFactory
import commandexecuter.controlcenter.service.CommandRunner
import commandexecuter.controlcenter.service.TextFilter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*
import java.net.URLDecoder

@RestController
@CrossOrigin(origins = ["*"])
class CommandResource(
        private val commandFactory: CommandFactory,
        private val commandRunner: CommandRunner,
        private val textFilter: TextFilter
) {

    @PostMapping("/command")
    fun resolveCommand(@RequestBody command: JsonNode): ResponseEntity<Void> {
        val commandTextValue = command.textValue().trim().toLowerCase()
        runCommand(commandTextValue)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/command/{command}")
    fun resolveCommandFromPath(@PathVariable("command") command: String): ResponseEntity<Void> {
        val commandTextValue = URLDecoder.decode(command, "UTF-8").toLowerCase()
        runCommand(commandTextValue)
        return ResponseEntity(HttpStatus.OK)
    }

    @Async
    fun runCommand(commandText: String) {
        val filteredCommandText = textFilter.filter(commandText)
        if (filteredCommandText.isNotEmpty())
            commandFactory.tryToCreateCommand(filteredCommandText)?.also { commandRunner.runCommand(it) }
    }
}