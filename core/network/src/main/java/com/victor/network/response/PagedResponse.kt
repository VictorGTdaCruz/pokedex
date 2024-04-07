package com.victor.network.response

data class PagedResponse<T>(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<T>? = null
)