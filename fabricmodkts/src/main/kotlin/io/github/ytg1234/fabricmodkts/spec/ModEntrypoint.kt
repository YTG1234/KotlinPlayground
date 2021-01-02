package io.github.ytg1234.fabricmodkts.spec

import io.github.ytg1234.fabricmodkts.FabricDsl

class ModEntrypoint(val cls: String) {
    @FabricDsl
    class Builder {
        lateinit var cls: String

        fun build(): ModEntrypoint {
            return ModEntrypoint(cls)
        }
    }

    companion object {
        fun create(body: Builder.() -> Unit): ModEntrypoint {
            val builder = Builder()
            builder.body()
            return builder.build()
        }
    }

    override fun toString(): String {
        return cls
    }
}

@FabricDsl
class EntrypointScope {
    val entrypoints = mutableMapOf<String, MutableList<ModEntrypoint>>()

    fun list(name: String, body: ListScope.() -> Unit) {
        val ls = ListScope()
        ls.body()
        entrypoints[name] = ls.entrypoints.toMutableList()
    }

    @FabricDsl
    class ListScope() {
        val entrypoints: MutableList<ModEntrypoint> = mutableListOf()

        fun add(body: ModEntrypoint.Builder.() -> Unit) {
            entrypoints.add(ModEntrypoint.create(body))
        }

        fun add(cls: String) = entrypoints.add(ModEntrypoint(cls))
    }
}
