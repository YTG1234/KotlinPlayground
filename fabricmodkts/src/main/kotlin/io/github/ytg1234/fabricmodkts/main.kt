package io.github.ytg1234.fabricmodkts

import io.github.ytg1234.fabricmodkts.ext.toJsonString
import io.github.ytg1234.fabricmodkts.spec.FabricModMetadata
import io.github.ytg1234.fabricmodkts.spec.cv.CvObject

@DslMarker
annotation class FabricDsl

fun main() {
    val meta = FabricModMetadata.create {
        id = "hello"
        version = "1.0.0"

        mixins {
            mixin {
                config = "abc.mixins.json"
            }
            mixin("hello.json")
        }

        accessWidener = "aw.accessWidener"

        custom {
            "modmenu:clientSideOnly" set true()
            "idorsomething" set "thevalue"()
            "ARR" {
                "abcd:abcd"(1())
            }
            "somearray"[
                    1(),
                    2(),
                    3(),
                    CvObject.create {
                        "aooinweds"(true())
                    }
            ]

            "array2" set listOf(
                69(),
                420(),
                "69420"()
            )
        }
    }
    println(meta.toJsonString())
}
