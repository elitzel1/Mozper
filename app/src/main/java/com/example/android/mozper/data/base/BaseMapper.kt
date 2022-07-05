package com.example.android.mozper.data.base

abstract class BaseMapper<Input, Output> {

    abstract fun transform(input: Input?): Output?

    fun transform(collection: Collection<Input>?): List<Output>? {
        if (collection == null) {
            return listOf()
        }
        val list = ArrayList<Output>()
        for (input in collection) {
            val entity = transform(input)
            if (entity != null) {
                list.add(entity)
            }
        }
        return list
    }
}
