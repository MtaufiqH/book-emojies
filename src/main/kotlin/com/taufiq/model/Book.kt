package com.taufiq.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id : String,
    val emoji: String,
    val title: String,
    val author: String
)