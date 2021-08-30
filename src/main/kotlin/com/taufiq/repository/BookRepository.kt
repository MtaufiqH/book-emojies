package com.taufiq.repository

import com.taufiq.model.Book

class BookRepository {
    // current book/ temporary.
    private val books = mutableListOf<Book>()

    fun getBooks() = books

    fun insert(book: Book) = books.add(book)

    fun update(updateBook: Book) = books.replaceAll { book ->
        if (book.id == updateBook.id) updateBook
        else book
    }

    fun delete(id: String) = books.removeIf{
        it.id == id
    }

}