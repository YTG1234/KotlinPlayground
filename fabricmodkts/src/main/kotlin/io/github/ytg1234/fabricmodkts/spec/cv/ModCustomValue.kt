package io.github.ytg1234.fabricmodkts.spec.cv

import com.google.gson.JsonElement
import io.github.ytg1234.fabricmodkts.FabricDsl
import io.github.ytg1234.fabricmodkts.spec.CVType

interface ModCustomValue {
    val type: CVType
    fun toJson(): JsonElement
}

@FabricDsl
class CustomValueScope(override val entries: MutableMap<String, ModCustomValue>) : UtilsProvider, ObjectUtils {
    constructor() : this(mutableMapOf())
}
