package io.github.ytg1234.fabricmodkts.spec

import io.github.ytg1234.fabricmodkts.FabricDsl

class ModLanguageAdapter(val cls: String, val name: String) {
    @FabricDsl
    class Builder {
        lateinit var cls: String
        lateinit var name: String

        fun build(): ModLanguageAdapter {
            return ModLanguageAdapter(cls, name)
        }
    }

    companion object {
        fun create(body: Builder.() -> Unit): ModLanguageAdapter {
            val builder = Builder()
            builder.body()
            return builder.build()
        }
    }

    override fun toString(): String {
        return "$name: $cls"
    }
}

@FabricDsl
class LanguageAdapterScope {
    val adapters: MutableList<ModLanguageAdapter> = mutableListOf()

    fun add(name: String, body: ModLanguageAdapter.Builder.() -> Unit) {
        val builder = ModLanguageAdapter.Builder()
        builder.name = name
        builder.body()
        adapters.add(builder.build())
    }
}
