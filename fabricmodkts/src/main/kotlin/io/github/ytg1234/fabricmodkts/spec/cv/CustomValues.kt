package io.github.ytg1234.fabricmodkts.spec.cv

import com.google.gson.*
import io.github.ytg1234.fabricmodkts.FabricDsl
import io.github.ytg1234.fabricmodkts.spec.CVType

open class CvObject(open val entries: MutableMap<String, ModCustomValue>) : ModCustomValue {
    override val type: CVType = CVType.Object
    override fun toJson(): JsonElement {
        val result = JsonObject()
        entries.forEach { (k, v) -> result.add(k, v.toJson()) }
        return result
    }

    @FabricDsl
    class Builder : UtilsProvider, ObjectUtils {
        override val entries: MutableMap<String, ModCustomValue> = mutableMapOf()

        fun build() = CvObject(entries)
    }

    companion object {
        fun create(body: Builder.() -> Unit): CvObject {
            val builder = Builder()
            builder.body()
            return builder.build()
        }
    }
}

class CvArray(val elements: MutableList<ModCustomValue>) : ModCustomValue {
    override val type: CVType = CVType.Array
    override fun toJson(): JsonElement {
        val result = JsonArray()
        elements.forEach { result.add(it.toJson()) }
        return result;
    }

    companion object {
        fun create(vararg c: ModCustomValue) = CvArray(c.toMutableList())
    }
}

class CvNumber(val value: Number) : ModCustomValue {
    override val type: CVType = CVType.Number
    override fun toJson() = JsonPrimitive(value)

    companion object {
        fun create(value: Number) = CvNumber(value)
    }
}

class CvBoolean(val value: Boolean) : ModCustomValue {
    override val type: CVType = CVType.Boolean
    override fun toJson() = JsonPrimitive(value)

    companion object {
        fun create(value: Boolean) = CvBoolean(value)
    }
}

class CvString(val value: String) : ModCustomValue {
    override val type: CVType = CVType.String
    override fun toJson() = JsonPrimitive(value)

    companion object {
        fun create(value: String) = CvString(value)
    }
}

class CvNull : ModCustomValue {
    override val type: CVType = CVType.Null
    override fun toJson(): JsonElement = JsonNull.INSTANCE
}
