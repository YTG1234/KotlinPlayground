package io.github.ytg1234.fabricmodkts.spec

enum class Dep {
    Depends,
    Recommends,
    Suggests,
    Conflicts,
    Breaks
}

enum class Env {
    Client,
    Server,
    Both;

    override fun toString(): String {
        return when(this) {
            Client -> "client"
            Server -> "server"
            Both -> "*"
        }
    }
}

enum class CVType {
    Object,
    Array,
    String,
    Number,
    Boolean,
    Null
}

typealias EnvType = Env
