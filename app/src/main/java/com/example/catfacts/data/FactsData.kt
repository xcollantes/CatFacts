package com.example.catfacts.data

data class FactsResponse(
    val current_page: Int,
    val data: List<CatFact>
)

data class CatFact(
    val fact: String,
    val length: Int
)
