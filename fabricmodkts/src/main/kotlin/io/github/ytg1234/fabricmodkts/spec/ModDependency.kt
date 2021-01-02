package io.github.ytg1234.fabricmodkts.spec

import io.github.ytg1234.fabricmodkts.FabricDsl

class ModDependency(val type: Dep, val id: String, val version: MutableList<String>) {
    @FabricDsl
    class Builder(val type: Dep) {
        lateinit var id: String
        val version: MutableList<String> = mutableListOf()

        fun build() = ModDependency(type, id, version)

        fun withVersion(vararg versions: String) {
            version.addAll(versions)
        }
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

    // region shortcuts
    fun depends(body: ModDependency.Builder.() -> Unit) = this.create(Dep.Depends, body)
    fun recommends(body: ModDependency.Builder.() -> Unit) = this.create(Dep.Recommends, body)
    fun suggests(body: ModDependency.Builder.() -> Unit) = this.create(Dep.Suggests, body)
    fun conflicts(body: ModDependency.Builder.() -> Unit) = this.create(Dep.Conflicts, body)
    fun breaks(body: ModDependency.Builder.() -> Unit) = this.create(Dep.Breaks, body)
    // endregion

    fun withDep(dep: ModDependency) {
        deps.add(dep)
    }
}
