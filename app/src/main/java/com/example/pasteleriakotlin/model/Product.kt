package com.example.pasteleriakotlin.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val imageRes: Int // en vez de URL usamos recurso drawable
)
