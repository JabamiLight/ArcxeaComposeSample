package com.example.app.model.entity

data class ArticleEntity(
    val title: String,
    var source: String,
    var timestamp: String,
    var content: String? = ""
)