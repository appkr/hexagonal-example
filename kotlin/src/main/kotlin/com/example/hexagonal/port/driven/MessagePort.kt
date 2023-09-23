package com.example.hexagonal.port.driven

interface MessagePort {
    fun send(aHypotheticalMessage: Any): Unit
}
