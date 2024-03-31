package com.victor.feature_pokedex.domain.service

import com.victor.feature_pokedex.domain.model.Generation
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonInformation
import com.victor.feature_pokedex.domain.model.PokemonSimple
import com.victor.feature_pokedex.domain.model.Specie
import com.victor.feature_pokedex.domain.model.Type
import com.victor.feature_pokedex.domain.model.TypeSimple

internal interface TypeRepository {

    companion object {
        internal val VALID_TYPE_ID_RANGE = 1 until 9999
    }

    suspend fun getTypeList(): List<TypeSimple>

    suspend fun getType(typeId: Int): Type
}