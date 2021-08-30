package com.taufiq.routes

import com.taufiq.model.Book
import com.taufiq.model.BookBody
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

fun Route.bookRouting() = route("/books") {

    // current book
    val books = mutableListOf<Book>()

    get {
        if (books.isNotEmpty()) {
            call.respond(HttpStatusCode.OK, books)
        } else {
            call.respondText("Not Found", status = HttpStatusCode.NotFound)
        }

    }

    post {
        val newBook = call.receive<BookBody>()
        books.add(newBook.toBook(id = UUID.randomUUID().toString()))
        call.respond(HttpStatusCode.OK, books)
    }

    put("/{id}") {
        val bookId = call.parameters["id"]
        val bookBody = call.receive<BookBody>()

        books.replaceAll { book ->
            if (book.id == bookId) bookBody.toBook(bookId) else book
        }

        call.respond(HttpStatusCode.OK, books)
    }

    delete("/{id}") {
        val bookId = call.parameters["id"]
        books.removeIf { book ->
            book.id == bookId
        }
        call.respond(HttpStatusCode.OK, books)
    }

}


fun Application.registerBookRoute() {
    routing {
        bookRouting()
    }
}