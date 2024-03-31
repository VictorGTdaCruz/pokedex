package com.victor.feature_pokedex.presentation.ui.utils

import androidx.compose.ui.graphics.Color
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.presentation.ui.theme.Bug
import com.victor.feature_pokedex.presentation.ui.theme.BugBackground
import com.victor.feature_pokedex.presentation.ui.theme.Dark
import com.victor.feature_pokedex.presentation.ui.theme.DarkBackground
import com.victor.feature_pokedex.presentation.ui.theme.Dragon
import com.victor.feature_pokedex.presentation.ui.theme.DragonBackground
import com.victor.feature_pokedex.presentation.ui.theme.Electric
import com.victor.feature_pokedex.presentation.ui.theme.ElectricBackground
import com.victor.feature_pokedex.presentation.ui.theme.Fairy
import com.victor.feature_pokedex.presentation.ui.theme.FairyBackground
import com.victor.feature_pokedex.presentation.ui.theme.Fighting
import com.victor.feature_pokedex.presentation.ui.theme.FightingBackground
import com.victor.feature_pokedex.presentation.ui.theme.Fire
import com.victor.feature_pokedex.presentation.ui.theme.FireBackground
import com.victor.feature_pokedex.presentation.ui.theme.Flying
import com.victor.feature_pokedex.presentation.ui.theme.FlyingBackground
import com.victor.feature_pokedex.presentation.ui.theme.Ghost
import com.victor.feature_pokedex.presentation.ui.theme.GhostBackground
import com.victor.feature_pokedex.presentation.ui.theme.Grass
import com.victor.feature_pokedex.presentation.ui.theme.GrassBackground
import com.victor.feature_pokedex.presentation.ui.theme.Ground
import com.victor.feature_pokedex.presentation.ui.theme.GroundBackground
import com.victor.feature_pokedex.presentation.ui.theme.Ice
import com.victor.feature_pokedex.presentation.ui.theme.IceBackground
import com.victor.feature_pokedex.presentation.ui.theme.Normal
import com.victor.feature_pokedex.presentation.ui.theme.NormalBackground
import com.victor.feature_pokedex.presentation.ui.theme.Poison
import com.victor.feature_pokedex.presentation.ui.theme.PoisonBackground
import com.victor.feature_pokedex.presentation.ui.theme.Psychic
import com.victor.feature_pokedex.presentation.ui.theme.PsychicBackground
import com.victor.feature_pokedex.presentation.ui.theme.Rock
import com.victor.feature_pokedex.presentation.ui.theme.RockBackground
import com.victor.feature_pokedex.presentation.ui.theme.Steel
import com.victor.feature_pokedex.presentation.ui.theme.SteelBackground
import com.victor.feature_pokedex.presentation.ui.theme.Unknown
import com.victor.feature_pokedex.presentation.ui.theme.Water
import com.victor.feature_pokedex.presentation.ui.theme.WaterBackground

private enum class PokemonTypeEnum(
    val id: Int,
    val image: Int,
    val color: Color,
    val backgroundColor: Color
) {
    BUG(id = 7, image = R.drawable.ic_type_bug, Bug, BugBackground),
    DARK(id = 17, image = R.drawable.ic_type_dark, Dark, DarkBackground),
    DRAGON(id = 16, image = R.drawable.ic_type_dragon, Dragon, DragonBackground),
    ELECTRIC(id = 13, image = R.drawable.ic_type_electric, Electric, ElectricBackground),
    FAIRY(id = 18, image = R.drawable.ic_type_fairy, Fairy, FairyBackground),
    FIGHTING(id = 2, image = R.drawable.ic_type_fighting, Fighting, FightingBackground),
    FIRE(id = 10, image = R.drawable.ic_type_fire, Fire, FireBackground),
    FLYING(id = 3, image = R.drawable.ic_type_flying, Flying, FlyingBackground),
    GHOST(id = 8, image = R.drawable.ic_type_ghost, Ghost, GhostBackground),
    GRASS(id = 12, image = R.drawable.ic_type_grass, Grass, GrassBackground),
    GROUND(id = 5, image = R.drawable.ic_type_ground, Ground, GroundBackground),
    ICE(id = 15, image = R.drawable.ic_type_ice, Ice, IceBackground),
    NORMAL(id = 1, image = R.drawable.ic_type_normal, Normal, NormalBackground),
    POISON(id = 4, image = R.drawable.ic_type_poison, Poison, PoisonBackground),
    PSYCHIC(id = 14, image = R.drawable.ic_type_psychic, Psychic, PsychicBackground),
    ROCK(id = 6, image = R.drawable.ic_type_rock, Rock, RockBackground),
    STEEL(id = 9, image = R.drawable.ic_type_steel, Steel, SteelBackground),
    WATER(id = 11, image = R.drawable.ic_type_water, Water, WaterBackground),
    SHADOW(id = 10002, image = R.drawable.ic_unknown, Unknown, Unknown),
    UNKNOWN(id = 10001, image = R.drawable.ic_unknown, Unknown, Unknown)
}

object TypeDrawableHelper {
    fun find(typeId: Int): Int =
        PokemonTypeEnum.values().find { it.id == typeId }
            ?.image
            ?: PokemonTypeEnum.UNKNOWN.image
}

object TypeColorHelper {
    fun find(typeId: Int): Color =
        PokemonTypeEnum.values().find { it.id == typeId }
            ?.color
            ?: PokemonTypeEnum.UNKNOWN.color

    fun findBackground(typeId: Int?): Color =
        PokemonTypeEnum.values().find { it.id == typeId }
            ?.backgroundColor
            ?: PokemonTypeEnum.UNKNOWN.backgroundColor
}