package com.victor.feature_pokedex.data.mapper

internal object IdMapper {

    fun fromTypeUrl(url: String?) = url.mapIdFromUrl(substringAfter = TYPE_SUBSTRING)

    fun fromPokemonUrl(url: String?) = url.mapIdFromUrl(substringAfter = POKEMON_SUBSTRING)

    private const val TYPE_SUBSTRING = "type/"
    private const val POKEMON_SUBSTRING = "pokemon/"
    private const val SLASH_SUBSTRING = "/"

    private fun String?.mapIdFromUrl(substringAfter: String) =
        this?.substringAfter(substringAfter)
            ?.substringBefore(SLASH_SUBSTRING)
            ?.toLongOrNull()
            ?: 0
}