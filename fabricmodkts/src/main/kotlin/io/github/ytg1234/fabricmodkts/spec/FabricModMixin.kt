package io.github.ytg1234.fabricmodkts.spec

import io.github.ytg1234.fabricmodkts.FabricDsl

class FabricModMixin(val config: String, val env: Env) {
    @FabricDsl
    class Builder {
        lateinit var config: String
        var env = Env.Both

        fun build() = FabricModMixin(config, env)
    }

    companion object {
        fun create(body: Builder.() -> Unit): FabricModMixin {
            val builder = Builder()
            builder.body()
            return builder.build()
        }
    }
}

@FabricDsl
class MixinScope {
    val mixins: MutableList<FabricModMixin> = mutableListOf()

    operator fun String.invoke() {
        mixins.add(FabricModMixin(this, Env.Both))
    }
    fun mixin(body: FabricModMixin.Builder.() -> Unit) {
        mixins.add(FabricModMixin.create(body))
    }
    fun mixin(config: String) {
        mixins.add(FabricModMixin(config, Env.Both))
    }
}
