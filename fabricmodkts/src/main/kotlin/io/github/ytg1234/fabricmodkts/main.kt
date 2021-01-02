package io.github.ytg1234.fabricmodkts

import io.github.ytg1234.fabricmodkts.dummy.DummyLanguageAdapter
import io.github.ytg1234.fabricmodkts.spec.Dep
import io.github.ytg1234.fabricmodkts.spec.Env
import io.github.ytg1234.fabricmodkts.spec.FabricModMetadata

@DslMarker
annotation class FabricDsl

fun main(args: Array<String>) {
    val modMeta =
        FabricModMetadata.create {
            name = "My mod!"
            id = "mymod"
            version = "1.0.0"
            environment = Env.Client

            dependencies {
                create(type = Dep.Depends) {
                    id = "minecraft"
                    version = "1.16.x"
                }
                create(type = Dep.Depends) {
                    id = "fabric"
                    version = ">=0.28.0"
                }
            }

            entrypoints {
                list(Any::class.java,"main") {
                    add {
                        cls = Any::class.java
                    }
                    add {
                        cls = Any::class.java
                    }
                }
                list<Map<*, *>>("maps") {
                    add {
                        cls = Map::class.java
                    }
                }
            }

            languageAdapters {
                add<DummyLanguageAdapter>("kotlin") {
                    cls = DummyLanguageAdapter::class.java
                }
            }
        }

    println(modMeta)
    println(modMeta.entrypoints)
    println(modMeta.adapters)
}
