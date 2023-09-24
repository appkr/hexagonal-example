package com.example.hexagonal.application.port.outbound

interface MessagePort {
    fun send(aHypotheticalMessage: Any): Unit
}
