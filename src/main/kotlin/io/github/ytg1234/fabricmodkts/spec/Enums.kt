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
    Both
}

typealias EnvType = Env
