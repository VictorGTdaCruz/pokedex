package com.victor.data.repository

import com.victor.model.Type
import com.victor.model.TypeSimple

internal interface TypeRepository {

    companion object {
        internal val VALID_TYPE_ID_RANGE = 1 until 9999
    }

    suspend fun getTypeList(): List<TypeSimple>

    suspend fun getType(typeId: Int): Type
}