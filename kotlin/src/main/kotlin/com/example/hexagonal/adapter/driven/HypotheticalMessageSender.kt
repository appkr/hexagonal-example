package com.example.hexagonal.adapter.driven

import com.example.hexagonal.port.driven.MessagePort
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

@Component
class HypotheticalMessageSender : MessagePort {

    val logger = KotlinLogging.logger {}

    override fun send(aHypotheticalMessage: Any) {
        logger.info { "Send a message: $aHypotheticalMessage" }
    }
}
