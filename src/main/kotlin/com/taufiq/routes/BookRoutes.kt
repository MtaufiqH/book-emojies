package com.taufiq.routes

import com.taufiq.model.BookBody
import com.taufiq.repository.BookRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*

/* JSON ROUTING
* GET 0.0.0.0:8080/books
* POST 0.0.0.0:8080/books
* PUT 0.0.0.0:8080/books/{id}
* DELETE 0.0.0.0:8080/books/{id}
* */

fun Route.bookRouting(repository: BookRepository = BookRepository()) =
    route("/books") {


        get {
            call.respond(HttpStatusCode.OK, repository.getBooks())

        }

        post {
            val newBook = call.receive<BookBody>()
            repository.insert(newBook.toBook(id = UUID.randomUUID().toString()))
            call.respond(HttpStatusCode.OK, repository.getBooks())
        }

        put("/{id}") {
            val bookId =
                call.parameters["id"] ?: return@put call.respondText(
                    "bad request",
                    status = HttpStatusCode.BadRequest)

            val bookBody = call.receive<BookBody>()

            repository.update(bookBody.toBook(bookId))
            call.respond(HttpStatusCode.OK, repository.getBooks())
        }

        delete("/{id}") {
            val bookId = call.parameters["id"] ?: return@delete call.respondText(
                "Bad Request",
                status = HttpStatusCode.BadRequest
            )
            repository.delete(bookId)
            call.respond(HttpStatusCode.OK, repository.getBooks())
        }

    }


fun Application.registerBookRoute() {
    routing {
        bookRouting()
    }
}