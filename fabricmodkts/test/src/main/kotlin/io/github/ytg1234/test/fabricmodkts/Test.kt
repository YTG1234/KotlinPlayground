package io.github.ytg1234.test.fabricmodkts

import io.github.ytg1234.fabricmodkts.ext.toJsonString
import io.github.ytg1234.fabricmodkts.script.ModMetadataScriptHost
import java.nio.file.Path
import kotlin.script.experimental.host.FileScriptSource

fun main() {
    val source = FileScriptSource(Path.of("fabric.mod.kts").toFile())
    println(ModMetadataScriptHost().getNew(source).toJsonString())
}
