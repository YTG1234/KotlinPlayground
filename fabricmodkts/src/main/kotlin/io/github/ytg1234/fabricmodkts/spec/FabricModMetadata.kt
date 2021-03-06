package io.github.ytg1234.fabricmodkts.spec

import io.github.ytg1234.fabricmodkts.FabricDsl
import io.github.ytg1234.fabricmodkts.spec.cv.CustomValueScope
import io.github.ytg1234.fabricmodkts.spec.cv.ModCustomValue

class FabricModMetadata(
    val name: String?,
    val id: String,
    val version: String,
    val deps: MutableList<ModDependency>,
    val entrypoints: MutableMap<String, MutableList<ModEntrypoint>>,
    val environment: Env,
    val adapters: MutableList<ModLanguageAdapter>,
    val description: String?,
    val contact: FabricModContact,
    val authors: MutableList<FabricModPerson>,
    val contributors: MutableList<FabricModPerson>,
    val license: MutableList<String>,
    val icon: String?,
    val mixins: MutableList<FabricModMixin>,
    val accessWidener: String?,
    val customValues: MutableMap<String, ModCustomValue>,
    val jars: MutableList<String>
) {
    @FabricDsl
    class Builder : FabricModMetadataBuilder {
        // Required
        override lateinit var id: String
        override lateinit var version: String

        // Optional
        override val deps = mutableListOf<ModDependency>()
        override var environment: Env = Env.Both
        override val entrypoints: MutableMap<String, MutableList<ModEntrypoint>> = mutableMapOf()
        override val adapters: MutableList<ModLanguageAdapter> = mutableListOf()
        override val jars: MutableList<String> = mutableListOf()

        // Metadata
        override var name: String? = null
        override var description: String? = null
        override var contact: FabricModContact = FabricModContact.EMPTY
        val authors: MutableList<FabricModPerson> = mutableListOf()
        val contributors: MutableList<FabricModPerson> = mutableListOf()
        override var license: MutableList<String> = mutableListOf()
        override var icon: String? = null
        override val mixins: MutableList<FabricModMixin> = mutableListOf()
        override val customValues: MutableMap<String, ModCustomValue> = mutableMapOf()
        override var accessWidener: String? = null

        override fun dependencies(body: DependenciesScope.() -> Unit) {
            val depScope = DependenciesScope()
            depScope.body()
            deps.addAll(depScope.deps)
        }

        override fun build() =
            FabricModMetadata(
                name,
                id,
                version,
                deps,
                entrypoints,
                environment,
                adapters,
                description,
                contact,
                authors,
                contributors,
                license,
                icon,
                mixins,
                accessWidener,
                customValues,
                jars
            )

        override fun entrypoints(body: EntrypointScope.() -> Unit) {
            val es = EntrypointScope()
            es.body()
            es.entrypoints.forEach {
                this.entrypoints[it.key] = it.value
            }
        }

        override fun languageAdapters(body: LanguageAdapterScope.() -> Unit) {
            val las = LanguageAdapterScope()
            las.body()
            this.adapters.addAll(las.adapters)
        }

        override fun contact(body: FabricModContact.Builder.() -> Unit) {
            contact = FabricModContact.create(body)
        }

        override fun authors(body: PersonScope.() -> Unit) {
            val scope = PersonScope()
            scope.body()
            authors.addAll(scope.persons)
        }

        override fun contributors(body: PersonScope.() -> Unit) {
            val scope = PersonScope()
            scope.body()
            contributors.addAll(scope.persons)
        }

        override fun mixins(body: MixinScope.() -> Unit) {
            val scope = MixinScope()
            scope.body()
            mixins.addAll(scope.mixins)
        }

        override fun custom(body: CustomValueScope.() -> Unit) {
            val scope = CustomValueScope()
            scope.body()
            customValues.putAll(scope.entries)
        }

        override fun jars(file: Boolean, jar: Boolean, body: JijScope.() -> Unit) {
            val scope = JijScope(checkForFile = file, checkForJar = jar)
            scope.body()
            jars.addAll(scope.paths)
        }
    }

    companion object {
        fun create(body: Builder.() -> Unit): FabricModMetadata {
            val builder = Builder()
            builder.body()
            return builder.build()
        }
    }
}

@FabricDsl
interface FabricModMetadataBuilder {
    var name: String?
    var id: String
    var version: String
    var description: String?
    var contact: FabricModContact
    var license: MutableList<String>
    var icon: String?
    var accessWidener: String?

    // Optional
    val deps: MutableList<ModDependency>
    var environment: Env
    val entrypoints: MutableMap<String, MutableList<ModEntrypoint>>
    val adapters: MutableList<ModLanguageAdapter>
    val mixins: MutableList<FabricModMixin>
    val customValues: MutableMap<String, ModCustomValue>
    val jars: MutableList<String>

    fun dependencies(body: DependenciesScope.() -> Unit)
    fun build(): FabricModMetadata
    fun entrypoints(body: EntrypointScope.() -> Unit)
    fun languageAdapters(body: LanguageAdapterScope.() -> Unit)
    fun contact(body: FabricModContact.Builder.() -> Unit)
    fun authors(body: PersonScope.() -> Unit)
    fun contributors(body: PersonScope.() -> Unit)
    fun mixins(body: MixinScope.() -> Unit)
    fun custom(body: CustomValueScope.() -> Unit)
    fun jars(file: Boolean = false, jar: Boolean = false, body: JijScope.() -> Unit)
}
