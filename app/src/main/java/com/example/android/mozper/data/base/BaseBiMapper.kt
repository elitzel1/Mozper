package com.example.android.mozper.data.base

abstract class BaseBiMapper<EntityD, EntityL> {
    abstract fun transformDtoL(entityD: EntityD?): EntityL?
    abstract fun transformLtoD(entityR: EntityL?): EntityD?

    fun transformDtoL(collection: Collection<EntityD>): List<EntityL>? {
        val list = mutableListOf<EntityL>()
        for (input in collection) {
            val entity = transformDtoL(input)
            if (entity != null) list.add(entity)
        }
        return list
    }

    fun transformLtoD(
        collection: Collection<EntityL>
    ): List<EntityD> {
        val list = mutableListOf<EntityD>()
        for (input in collection) {
            val entity = transformLtoD(input)
            if (entity != null) list.add(entity)
        }
        return list
    }
}
