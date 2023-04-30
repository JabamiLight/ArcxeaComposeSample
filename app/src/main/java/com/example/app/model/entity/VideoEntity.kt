package com.example.app.model.entity

data class VideoEntity(
    val type: String = "",
    val title: String,
    val duration: String,
    val imageUrl: String,
    val video: String? = "",
    val desc: String? = ""
)