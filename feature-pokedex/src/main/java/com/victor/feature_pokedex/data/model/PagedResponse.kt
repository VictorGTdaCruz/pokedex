package com.victor.feature_pokedex.data.model

data class PagedResponse<T>(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<T>? = null
)