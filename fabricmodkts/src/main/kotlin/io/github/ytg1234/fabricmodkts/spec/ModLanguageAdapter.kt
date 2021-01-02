package io.github.ytg1234.fabricmodkts.spec

import io.github.ytg1234.fabricmodkts.FabricDsl
import io.github.ytg1234.fabricmodkts.dummy.DummyLanguageAdapter

class ModLanguageAdapter<T : DummyLanguageAdapter>(val cls: Class<T>, val name: String) {
    @FabricDsl
    class Builder<T : DummyLanguageAdapter> {
        lateinit var cls: Class<T>
        lateinit var name: String

        fun build(): ModLanguageAdapter<T> {
            return ModLanguageAdapter(cls, name)
        }
    }

    companion object {
        fun <T : DummyLanguageAdapter> create(body: Builder<T>.() -> Unit): ModLanguageAdapter<T> {
            val builder = Builder<T>()
            builder.body()
            return builder.build()
        }
    }

    override fun toString(): String {
        return name + ": " + cls.name
    }
}

@FabricDsl
class LanguageAdapterScope {
    val adapters: MutableList<ModLanguageAdapter<*>> = mutableListOf()

    fun <T : DummyLanguageAdapter> add(name: String, body: ModLanguageAdapter.Builder<T>.() -> Unit) {
        val builder = ModLanguageAdapter.Builder<T>()
        builder.name = name
        builder.body()
        adapters.add(builder.build())
    }
}
