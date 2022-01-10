package com.victor.pokedex.presentation.ui.utils

import androidx.compose.ui.graphics.Color
import com.victor.pokedex.R
import com.victor.pokedex.presentation.ui.theme.Bug
import com.victor.pokedex.presentation.ui.theme.Dark
import com.victor.pokedex.presentation.ui.theme.Dragon
import com.victor.pokedex.presentation.ui.theme.Electric
import com.victor.pokedex.presentation.ui.theme.Fairy
import com.victor.pokedex.presentation.ui.theme.Fighting
import com.victor.pokedex.presentation.ui.theme.Fire
import com.victor.pokedex.presentation.ui.theme.Flying
import com.victor.pokedex.presentation.ui.theme.Ghost
import com.victor.pokedex.presentation.ui.theme.Grass
import com.victor.pokedex.presentation.ui.theme.Ground
import com.victor.pokedex.presentation.ui.theme.Ice
import com.victor.pokedex.presentation.ui.theme.Normal
import com.victor.pokedex.presentation.ui.theme.Poison
import com.victor.pokedex.presentation.ui.theme.Psychic
import com.victor.pokedex.presentation.ui.theme.Rock
import com.victor.pokedex.presentation.ui.theme.Steel
import com.victor.pokedex.presentation.ui.theme.Unknown
import com.victor.pokedex.presentation.ui.theme.Water

private enum class PokemonTypeEnum(
    val id: Long,
    val image: Int,
    val color: Color
) {
    BUG(id = 7, image = R.drawable.ic_type_bug, Bug),
    DARK(id = 17, image = R.drawable.ic_type_dark, Dark),
    DRAGON(id = 16, image = R.drawable.ic_type_dragon, Dragon),
    ELECTRIC(id = 13, image = R.drawable.ic_type_electric, Electric),
    FAIRY(id = 18, image = R.drawable.ic_type_fairy, Fairy),
    FIGHTING(id = 2, image = R.drawable.ic_type_fighting, Fighting),
    FIRE(id = 10, image = R.drawable.ic_type_fire, Fire),
    FLYING(id = 3, image = R.drawable.ic_type_flying, Flying),
    GHOST(id = 8, image = R.drawable.ic_type_ghost, Ghost),
    GRASS(id = 12, image = R.drawable.ic_type_grass, Grass),
    GROUND(id = 5, image = R.drawable.ic_type_ground, Ground),
    ICE(id = 15, image = R.drawable.ic_type_ice, Ice),
    NORMAL(id = 1, image = R.drawable.ic_type_normal, Normal),
    POISON(id = 4, image = R.drawable.ic_type_poison, Poison),
    PSYCHIC(id = 14, image = R.drawable.ic_type_psychic, Psychic),
    ROCK(id = 6, image = R.drawable.ic_type_rock, Rock),
    STEEL(id = 9, image = R.drawable.ic_type_steel, Steel),
    WATER(id = 11, image = R.drawable.ic_type_water, Water),
    SHADOW(id = 10002, image = R.drawable.ic_unknown, Unknown),
    UNKNOWN(id = 10001, image = R.drawable.ic_unknown, Unknown)
}

object TypeDrawableHelper {
    fun find(typeId: Long): Int =
        PokemonTypeEnum.values().find { it.id == typeId }?.image
            ?: PokemonTypeEnum.UNKNOWN.image
}

object TypeColorHelper {
    fun find(typeId: Long): Color =
        PokemonTypeEnum.values().find { it.id == typeId }?.color
            ?: PokemonTypeEnum.UNKNOWN.color
}