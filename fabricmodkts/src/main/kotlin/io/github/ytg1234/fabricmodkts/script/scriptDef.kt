package io.github.ytg1234.fabricmodkts.script

import io.github.ytg1234.fabricmodkts.spec.FabricModMetadataBuilder
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.jvm.dependenciesFromClassContext
import kotlin.script.experimental.jvm.jvm

@KotlinScript(
    displayName = "Fabric Mod Metadata Script",
    compilationConfiguration = ModMetadataScriptCompilationConfiguration::class,
    fileExtension = "mod.kts"
)
abstract class ModMetadataScript(builder: FabricModMetadataBuilder) : FabricModMetadataBuilder by builder

object ModMetadataScriptCompilationConfiguration : ScriptCompilationConfiguration({
    jvm {
        dependenciesFromClassContext(
            ModMetadataScript::class,
            "kotlin-stdlib",
            "fabricmodkts"
        )
    }

    ide {
        acceptedLocations(ScriptAcceptedLocation.Everywhere)
    }

    defaultImports(
        "Dep",
        "Env"
    )
})
