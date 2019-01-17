package controlcenter.web

import com.fasterxml.jackson.databind.JsonNode
import controlcenter.service.CommandFactory
import controlcenter.service.CommandRunner
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URLDecoder

@RestController
@CrossOrigin(origins = ["*"])
class CommandResource(
        private val commandFactory: CommandFactory,
        private val commandRunner: CommandRunner
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

    fun runCommand(commandText: String) = commandFactory.tryToCreateCommand(commandText)?.also { commandRunner.runCommand(it) }
}