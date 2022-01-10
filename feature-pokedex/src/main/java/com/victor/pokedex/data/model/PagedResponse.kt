package com.victor.pokedex.data.model

data class PagedResponse<T>(
    val count: Int? = null,
    val next: Int? = null,
    val previous: Int? = null,
    val results: List<T>? = null
)