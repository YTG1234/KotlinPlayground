package io.github.ytg1234.fabricmodkts.spec

import io.github.ytg1234.fabricmodkts.FabricDsl

class FabricModPerson(
    val name: String,
    val contact: FabricModContact
) {
    @FabricDsl
    class Builder {
        lateinit var name: String
        var contact = FabricModContact.EMPTY

        fun contact(body: FabricModContact.Builder.() -> Unit) {
            contact = FabricModContact.create(body)
        }

        fun build() = FabricModPerson(name, contact)
    }

    companion object {
        fun create(body: Builder.() -> Unit): FabricModPerson {
            val builder = Builder()
            builder.body()
            return builder.build()
        }
    }
}

@FabricDsl
class PersonScope {
    val persons: MutableList<FabricModPerson> = mutableListOf()

    fun add(name: String) {
        persons.add(FabricModPerson(name, FabricModContact.EMPTY))
    }

    fun add(name: String, body: FabricModContact.Builder.() -> Unit) {
        persons.add(FabricModPerson(name, FabricModContact.create(body)))
    }

    fun add(body: FabricModPerson.Builder.() -> Unit) {
        persons.add(FabricModPerson.create(body))
    }
}
