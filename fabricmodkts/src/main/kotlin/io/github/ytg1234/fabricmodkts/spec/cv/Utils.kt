package io.github.ytg1234.fabricmodkts.spec.cv

interface UtilsProvider {
    operator fun Number.invoke() = CvNumber(this)
    operator fun Boolean.invoke() = CvBoolean(this)
    operator fun Nothing?.invoke() = CvNull()
    operator fun String.invoke() = CvString(this)

    operator fun Number.unaryPlus() = this()
    operator fun Boolean.unaryPlus() = this()
    operator fun Nothing?.unaryPlus() = this()
    operator fun String.unaryPlus() = this()

    operator fun Number.unaryMinus() = this()
    operator fun Boolean.unaryMinus() = this()
    operator fun Nothing?.unaryMinus() = this()
    operator fun String.unaryMinus() = this()

    operator fun invoke(body: CvObject.Builder.() -> Unit): CvObject {
        val builder = CvObject.Builder()
        builder.body()
        return builder.build()
    }
}

interface ObjectUtils {
    val entries: MutableMap<String, ModCustomValue>
    operator fun String.invoke(c: ModCustomValue) {
        this set c
    }

    operator fun String.invoke(body: CvObject.Builder.() -> Unit) {
        this set body
    }

    operator fun String.get(vararg c: ModCustomValue) {
        this set c.toMutableList()
    }

    infix fun String.set(c: ModCustomValue) {
        entries[this] = c
    }

    infix fun String.set(c: Array<ModCustomValue>) {
        this set c.toMutableList()
    }

    infix fun String.set(body: CvObject.Builder.() -> Unit) {
        val builder = CvObject.Builder()
        builder.body()
        entries[this] = builder.build()
    }

    infix fun String.set(c: List<ModCustomValue>) {
        entries[this] = CvArray(c.toMutableList())
    }

    operator fun String.minus(c: ModCustomValue) = this(c)
}
