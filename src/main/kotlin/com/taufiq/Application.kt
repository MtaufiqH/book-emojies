package com.taufiq

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.taufiq.plugins.*
import com.taufiq.routes.configureRouting
import com.taufiq.routes.registerBookRoute
import io.ktor.application.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureMonitoring()
        configureHTTP()
        configureRouting()
        configureSecurity()
        registerBookRoute()
    }.start(wait = true)
}
