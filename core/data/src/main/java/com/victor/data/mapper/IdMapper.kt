package com.victor.data.mapper

internal object IdMapper {

    private const val SLASH_SUBSTRING = "/"

    fun mapIdFromUrl(url: String?) =
        url?.removeSuffix(SLASH_SUBSTRING)
            ?.substringAfterLast(SLASH_SUBSTRING)
            ?.toIntOrNull()
            ?: 0
}