package com.victor.feature_pokedex.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.victor.feature_pokedex.data.mapper.toPokemonListDomain
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.networking.PokedexException
import com.victor.networking.request
import kotlinx.coroutines.delay
import java.net.UnknownHostException

internal class PokemonPagingDataSource(private val api: PokedexGateway): PagingSource<Int, Pokemon>() {

    companion object {
        private const val OFFSET_SIZE = 20
        private const val OFFSET_URL_STRING_KEY = "offset="
        private const val LIMIT_URL_STRING_KEY = "&limit"
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            request {
                delay(10000)
                val currentPage = params.key
                if (currentPage == 4) throw UnknownHostException()
                val response = api.getPokemonList(
                    offset = currentPage?.getOffsetFromPageNumber() ?: 0,
                    limit = OFFSET_SIZE
                )
                LoadResult.Page(
                    data = response.toPokemonListDomain(),
                    nextKey = response.next?.getPageNumberFromUrlOffset(),
                    prevKey = response.previous?.getPageNumberFromUrlOffset(),
                )
            }
        } catch(pokedexException: PokedexException) {
            LoadResult.Error(pokedexException)
        }
    }

    private fun Int.getOffsetFromPageNumber() = times(OFFSET_SIZE)

    private fun String.getPageNumberFromUrlOffset() =
        substringAfter(OFFSET_URL_STRING_KEY)
        .substringBefore(LIMIT_URL_STRING_KEY)
        .toInt()
        .div(OFFSET_SIZE)
}