package me.tom.library


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
