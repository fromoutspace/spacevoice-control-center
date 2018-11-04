package commandexecuter.controlcenter

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Strings
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.Environment
import org.springframework.util.StringUtils
import java.net.InetAddress
import java.net.UnknownHostException

@SpringBootApplication
class ControlCenterApplication

fun main(args: Array<String>) {
    val app = runApplication<ControlCenterApplication>(*args)
    logApplicationStartup(app.environment)
}


private fun logApplicationStartup(env: Environment) {
    val log = LogManager.getLogger()

    var protocol = "http"
    if (env.getProperty("server.ssl.key-store") != null) {
        protocol = "https"
    }
    val serverPort = env.getProperty("server.port") ?: "8080"
    var contextPath = env.getProperty("server.servlet.context-path")
    if (Strings.isBlank(contextPath)) {
        contextPath = "/"
    }
    var hostAddress = "localhost"
    try {
        hostAddress = InetAddress.getLocalHost().hostAddress
    } catch (e: UnknownHostException) {
        log.warn("The host name could not be determined, using `localhost` as fallback")
    }

    log.info("\n----------------------------------------------------------\n\t" +
            "Application is running! Access URLs:\n\t" +
            "Local: \t\t{}://localhost:{}{}\n\t" +
            "External: \t{}://{}:{}{}" +
            "\n----------------------------------------------------------",
            protocol,
            serverPort,
            contextPath,
            protocol,
            hostAddress,
            serverPort,
            contextPath)
}
