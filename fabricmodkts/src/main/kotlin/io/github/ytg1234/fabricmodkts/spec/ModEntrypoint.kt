package io.github.ytg1234.fabricmodkts.spec

import io.github.ytg1234.fabricmodkts.FabricDsl

class ModEntrypoint<T>(val cls: Class<T>) {
    @FabricDsl
    class Builder<T> {
        lateinit var cls: Class<T>

        fun build(): ModEntrypoint<T> {
            return ModEntrypoint(cls)
        }
    }

    companion object {
        fun <T> create(body: Builder<T>.() -> Unit): ModEntrypoint<T> {
            val builder = Builder<T>()
            builder.body()
            return builder.build()
        }
    }

    override fun toString(): String {
        return cls.name
    }
}

@FabricDsl
class EntrypointScope {
    val entrypoints = mutableMapOf<String, MutableList<ModEntrypoint<*>>>()

    fun <T> list(name: String, body: ListScope<T>.() -> Unit) {
        val ls = ListScope<T>()
        ls.body()
        entrypoints[name] = ls.entrypoints.toMutableList()
    }

    fun <T> list(clazz: Class<T>, name: String, body: ListScope<T>.() -> Unit) = list(name, body)

    @FabricDsl
    class ListScope<T>() {
        val entrypoints: MutableList<ModEntrypoint<T>> = mutableListOf()

        fun add(body: ModEntrypoint.Builder<T>.() -> Unit) {
            entrypoints.add(ModEntrypoint.create(body))
        }

        fun add(cls: Class<T>) = entrypoints.add(ModEntrypoint(cls))
    }
}
