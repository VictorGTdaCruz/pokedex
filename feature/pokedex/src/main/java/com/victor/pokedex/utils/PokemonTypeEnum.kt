package com.victor.pokedex.utils

import androidx.compose.ui.graphics.Color
import com.victor.pokedex.R
import com.victor.pokedex.theme.Bug
import com.victor.pokedex.theme.BugBackground
import com.victor.pokedex.theme.Dark
import com.victor.pokedex.theme.DarkBackground
import com.victor.pokedex.theme.Dragon
import com.victor.pokedex.theme.DragonBackground
import com.victor.pokedex.theme.Electric
import com.victor.pokedex.theme.ElectricBackground
import com.victor.pokedex.theme.Fairy
import com.victor.pokedex.theme.FairyBackground
import com.victor.pokedex.theme.Fighting
import com.victor.pokedex.theme.FightingBackground
import com.victor.pokedex.theme.Fire
import com.victor.pokedex.theme.FireBackground
import com.victor.pokedex.theme.Flying
import com.victor.pokedex.theme.FlyingBackground
import com.victor.pokedex.theme.Ghost
import com.victor.pokedex.theme.GhostBackground
import com.victor.pokedex.theme.Grass
import com.victor.pokedex.theme.GrassBackground
import com.victor.pokedex.theme.Ground
import com.victor.pokedex.theme.GroundBackground
import com.victor.pokedex.theme.Ice
import com.victor.pokedex.theme.IceBackground
import com.victor.pokedex.theme.Normal
import com.victor.pokedex.theme.NormalBackground
import com.victor.pokedex.theme.Poison
import com.victor.pokedex.theme.PoisonBackground
import com.victor.pokedex.theme.Psychic
import com.victor.pokedex.theme.PsychicBackground
import com.victor.pokedex.theme.Rock
import com.victor.pokedex.theme.RockBackground
import com.victor.pokedex.theme.Steel
import com.victor.pokedex.theme.SteelBackground
import com.victor.pokedex.theme.Unknown
import com.victor.pokedex.theme.Water
import com.victor.pokedex.theme.WaterBackground

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