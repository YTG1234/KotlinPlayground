package io.github.ytg1234.test.fabricmodkts

import io.github.ytg1234.fabricmodkts.ext.toJsonString
import io.github.ytg1234.fabricmodkts.spec.Dep
import io.github.ytg1234.fabricmodkts.spec.Env
import io.github.ytg1234.fabricmodkts.spec.FabricModMetadata

fun main() {
    println(
        FabricModMetadata.create {
            id = "hello"
            version = "1.0.3"
            name = "The Mod"
            description = """
                This is a mod
                that does thigns.
            """.trimIndent()

            contact {
                email = "my@email.io"
                irc = "https://example/#ircchannel"
                homepage = "https://example.com/"
                issues = "https://example.com/issues"
                sources = "https://example.com/sourcecode"
                addMore("discord", "discord.gg/abcdefghijklmnop")
            }

            authors {
                add("YTG1234")
                add("Me!") {
                    // normal contact scope here
                    email = "me@email.com"
                }
                add {
                    name = "Not Me!"
                    contact {
                        email = "me@email.com"
                    }
                }
            }

            // contributors: same as authors

            license.add("MIT")
            icon = "assets/hello/icon.png"

            environment = Env.Both

            dependencies {
                depends {
                    id = "minecraft"
                    withVersion("1.16.x")
                }
                suggests {
                    id = "flamingo"
                    withVersion("*")
                }
                breaks {
                    id = "optifabric"
                    withVersion("*")
                }
            }

            entrypoints {
                list("main") {
                    add("com.example.ExampleModInitializer")
                }
                list("client") {
                    add("com.example.ExampleClientModInitializer")
                }
            }

            languageAdapters {
                add("cpp") {
                    cls = "com.example.CppLanguageAdapter"
                }
            }

            mixins {
                mixin("mixins.hello.json")
            }

            accessWidener = "hello.accessWidener"

            custom {
                "modmenu:api" set true()
            }
        }.toJsonString()
    )
}
