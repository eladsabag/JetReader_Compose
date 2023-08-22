package com.elads.booksapp.model

data class BookModel(
    var id: String? = null,
    var title: String? = null,
    var authors: String? = null,
    var notes: String? = null,
    var url: String? = "https://books.google.com/books/content?id=JGH0DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
)
