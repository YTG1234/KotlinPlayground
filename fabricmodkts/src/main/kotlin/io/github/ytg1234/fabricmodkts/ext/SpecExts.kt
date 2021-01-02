package io.github.ytg1234.fabricmodkts.ext

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import io.github.ytg1234.fabricmodkts.spec.*

private val String.j: JsonElement
    get() = JsonPrimitive(this)
private val Int.j: JsonElement
    get() = JsonPrimitive(this)

fun FabricModMetadata.toJson(): JsonObject {
    val result = JsonObject()
    result.add("schemaVersion", 1.j)

    // Mandatory Fields
    result.add("id", this.id.j)
    result.add("version", this.version.j)

    // Optional Fields
    result.add(
        "environment", environment.toString().j
    )

    // Entrypoints
    if (entrypoints.isNotEmpty()) {
        val jsonEntrypoints = JsonObject()
        for (entry in entrypoints) {
            val arr = JsonArray()
            entry.value.forEach { arr.add(it.cls.name) }
            jsonEntrypoints.add(entry.key, arr)
        }
        result.add("entrypoints", jsonEntrypoints)
    }

    // Adapters
    if (adapters.isNotEmpty()) {
        val jsonAdapters = JsonObject()
        adapters.forEach { jsonAdapters.add(it.name, it.cls.name.j) }
        result.add("languageAdapters", jsonAdapters)
    }

    // Dependencies
    for (dependency in deps) {
        val depTypeJson = result.addIfAbsent(
            when (dependency.type) {
                Dep.Breaks -> "breaks"
                Dep.Conflicts -> "conflicts"
                Dep.Suggests -> "suggests"
                Dep.Recommends -> "recommends"
                Dep.Depends -> "depends"
            }, JsonObject()
        )
        val versions = JsonArray()
        dependency.version.forEach(versions::add)
        depTypeJson.add(dependency.id, versions)
    }

    // Metadata
    result.add("name", name?.j ?: id.j)
    if (icon != null) result.add("icon", icon.j)
    if (contact != FabricModContact.EMPTY) result.add("contact", contact.toJson())

    // Authors
    if (authors.isNotEmpty()) {
        val authorsJson = JsonArray()
        authors.forEach { authorsJson.add(it.toJson()) }
        result.add("authors", authorsJson)
    }

    // Contributors
    if (contributors.isNotEmpty()) {
        val contributorsJson = JsonArray()
        contributors.forEach { contributorsJson.add(it.toJson()) }
        result.add("contributors", contributorsJson)
    }

    // License
    if (license.isNotEmpty()) {
        val licenseJson = JsonArray()
        license.forEach(licenseJson::add)
    }

    // Mixins
    if (mixins.isNotEmpty()) {
        val mixinsJson = JsonArray()
        mixins.forEach { mixinsJson.add(it.toJson()) }
        result.add("mixins", mixinsJson)
    }

    // Access Widener
    if (accessWidener != null) result.add("accessWidener", accessWidener.j)

    return result
}

fun FabricModMixin.toJson(): JsonObject {
    val obj = JsonObject()
    obj.add("config", config.j)
    obj.add("environment", env.toString().j)
    return obj
}

fun FabricModContact.toJson(): JsonObject {
    val contactJson = JsonObject()
    if (email != null) contactJson.add("email", email.j)
    if (irc != null) contactJson.add("irc", irc.j)
    if (homepage != null) contactJson.add("homepage", homepage.j)
    if (issues != null) contactJson.add("issues", issues.j)
    if (sources != null) contactJson.add("sources", sources.j)
    if (additional.isNotEmpty()) {
        for (entry in additional) {
            contactJson.add(entry.key, entry.value.j)
        }
    }
    return contactJson
}

fun FabricModPerson.toJson(): JsonObject {
    val personJson = JsonObject()
    personJson.add("name", name.j)
    if (contact != FabricModContact.EMPTY) personJson.add("contact", contact.toJson())
    return personJson
}

@Suppress("UNCHECKED_CAST")
private fun <T : JsonElement> JsonObject.newIfAbsent(prop: String, value: T): T {
    if (this[prop] == null) return value
    return this[prop]!! as T
}

@Suppress("UNCHECKED_CAST")
private fun <T : JsonElement> JsonObject.addIfAbsent(prop: String, value: T): T {
    if (this[prop] == null) add(prop, value)
    return this[prop]!! as T
}
