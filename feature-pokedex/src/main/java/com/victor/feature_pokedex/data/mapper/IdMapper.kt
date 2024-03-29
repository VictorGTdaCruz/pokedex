package com.victor.feature_pokedex.data.mapper

internal object IdMapper {

    private const val SLASH_SUBSTRING = "/"

    fun mapIdFromUrl(url: String?) =
        url?.removeSuffix(SLASH_SUBSTRING)
            ?.substringAfterLast(SLASH_SUBSTRING)
            ?.toLongOrNull()
            ?: 0
}