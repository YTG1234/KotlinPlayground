package io.github.ytg1234.fabricmodkts.script

import io.github.ytg1234.fabricmodkts.spec.FabricModMetadata
import io.github.ytg1234.fabricmodkts.spec.FabricModMetadataBuilder
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultValue
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.SourceCode
import kotlin.script.experimental.api.constructorArgs
import kotlin.script.experimental.api.valueOrThrow
import kotlin.script.experimental.host.StringScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost

class ModMetadataScriptHost {
    private val scriptingHost = BasicJvmScriptingHost()

    fun eval(
        code: SourceCode,
        meta: FabricModMetadataBuilder
    ): ResultWithDiagnostics<EvaluationResult> =
        scriptingHost.evalWithTemplate<ModMetadataScript>(
            code,
            evaluation = {
                constructorArgs(meta)
            }
        )

    fun getNew(code: SourceCode): FabricModMetadata {
        val res = eval(StringScriptSource(code.text + "\nbuild()\n", "fabric.mod.kts"), FabricModMetadata.Builder())
        return when (val ret = res.valueOrThrow().returnValue) {
            is ResultValue.Value -> ret.value as FabricModMetadata
            is ResultValue.Error -> throw RuntimeException("Error while executing script", ret.error)
            else -> throw RuntimeException("Something went wrong: $ret")
        }
    }
}
