package io.github.ytg1234.fabricmodkts.spec

import io.github.ytg1234.fabricmodkts.FabricDsl

class ModDependency(val type: Dep, val id: String, val version: String) {
    @FabricDsl
    class Builder(val type: Dep) {
        lateinit var id: String
        lateinit var version: String

        fun build() = ModDependency(type, id, version)
    }

    override fun toString(): String {
        return """
            Fabric mod dependency:
            Type: ${type.name}
            Dependant ID: $id
            Dependant Version Range: $version
        """.trimIndent()
    }

    companion object {
        fun create(type: Dep, body: Builder.() -> Unit): ModDependency {
            val builder = Builder(type)
            builder.body()
            return builder.build()
        }
    }
}

@FabricDsl
class DependenciesScope {
    val deps = mutableListOf<ModDependency>()

    fun create(type: Dep, body: ModDependency.Builder.() -> Unit) {
        this.withDep(ModDependency.create(type, body))
    }

    fun withDep(dep: ModDependency) {
        deps.add(dep)
    }
}