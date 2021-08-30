package com.taufiq.routes

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.serialization.*

fun Application.configureRouting() {

    install(ContentNegotiation){
        json()
    }

    routing {
        install(StatusPages) {
            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }

        }
    }
}
class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
