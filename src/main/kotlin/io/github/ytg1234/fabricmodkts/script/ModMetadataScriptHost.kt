package io.github.ytg1234.fabricmodkts.script

import io.github.ytg1234.fabricmodkts.spec.FabricModMetadataBuilder
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.SourceCode
import kotlin.script.experimental.api.constructorArgs
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
}
