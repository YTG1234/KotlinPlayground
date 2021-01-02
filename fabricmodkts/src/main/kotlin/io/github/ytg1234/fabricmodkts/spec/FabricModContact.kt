package io.github.ytg1234.fabricmodkts.spec

import io.github.ytg1234.fabricmodkts.FabricDsl

class FabricModContact(
    val email: String?,
    val irc: String?,
    val homepage: String?,
    val issues: String?,
    val sources: String?,
    val additional: MutableMap<String, String>
) {
    @FabricDsl
    class Builder {
        var email: String? = null
        var irc: String? = null
        var homepage: String? = null
        var issues: String? = null
        var sources: String? = null

        private var additionals: MutableMap<String, String> = mutableMapOf()

        fun build(): FabricModContact {
            return FabricModContact(email, irc, homepage, issues, sources, additionals)
        }

        fun addMore(key: String, value: String) {
            additionals[key] = value
        }
    }

    companion object {
        fun create(body: Builder.() -> Unit): FabricModContact {
            val builder = Builder()
            builder.body()
            return builder.build()
        }

        val EMPTY = FabricModContact("", "", "", "", "", mutableMapOf())
    }
}
