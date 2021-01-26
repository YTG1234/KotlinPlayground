package io.github.ytg1234.fabricmodkts.spec

import io.github.ytg1234.fabricmodkts.FabricDsl
import java.io.File
import java.nio.file.Path

@FabricDsl
class JijScope(val checkForFile: Boolean = false, val checkForJar: Boolean = false) {
    val paths: MutableList<String> = mutableListOf()

    operator fun String.unaryPlus() {
        if (checkForFile && !pathOf(this).toFile().exists()) throw IllegalArgumentException("Supplied file $this doesn't exist!")
        if (checkForJar && !endsWith(".jar")) throw java.lang.IllegalArgumentException("Supplied file $this is not a jar file!")
        paths.add(this)
    }
    fun from(directory: String) {
        val path = pathOf(directory)

        if (!path.toFile().isDirectory) throw IllegalArgumentException("Specified Jar path $directory is not a directory!")
        for (file in path.toFile().list()!!) {
            if (checkForFile && Path.of(file).toFile().isDirectory) continue
            if (checkForJar && !file.endsWith(".jar")) throw RuntimeException("File $file doesn't have the .jar extension!")
            if (!paths.contains(directory + File.separator + file)) paths.add(directory + File.separator + file)
        }
    }

    private fun pathOf(l: String) = Path.of(l.replace('/', File.separatorChar))
}
