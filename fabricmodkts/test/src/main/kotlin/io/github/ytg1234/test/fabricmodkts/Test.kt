package io.github.ytg1234.test.fabricmodkts

import io.github.ytg1234.fabricmodkts.ext.toJson
import io.github.ytg1234.fabricmodkts.script.ModMetadataScriptHost
import io.github.ytg1234.fabricmodkts.spec.FabricModMetadata
import java.nio.file.Path
import kotlin.script.experimental.api.ResultValue
import kotlin.script.experimental.api.valueOrNull
import kotlin.script.experimental.api.valueOrThrow
import kotlin.script.experimental.host.FileScriptSource

fun main() {
    val source = FileScriptSource(Path.of("fabric.mod.kts").toFile())
    val res = ModMetadataScriptHost().eval(source, FabricModMetadata.Builder())
    val resultValue = res.valueOrThrow().returnValue
    if (resultValue is ResultValue.Value) {
        val modMeta = resultValue.value as FabricModMetadata
        println(modMeta.toJson().toString())
    }
}
