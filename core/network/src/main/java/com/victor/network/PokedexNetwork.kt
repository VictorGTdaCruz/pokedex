package com.victor.network

internal class PokedexNetwork(private val api: PokedexApi) : PokedexDataSource {

    override suspend fun getPokemonList() = request {
        api.getPokemonList()
    }

    override suspend fun getPokemon(pokemonId: Int) = request {
        api.getPokemon(pokemonId)
    }

    override suspend fun getGeneration(generationId: Int) = request {
        api.getGeneration(generationId)
    }

    override suspend fun getSpecie(pokemonId: Int) = request {
        api.getSpecie(pokemonId)
    }

    override suspend fun getEvolution(evolutionChainId: Int) = request {
        api.getEvolutionChain(evolutionChainId)
    }

    override suspend fun getType(typeId: Int) = request {
        api.getType(typeId)
    }

    override suspend fun getTypeList() = request {
        api.getTypeList()
    }
}