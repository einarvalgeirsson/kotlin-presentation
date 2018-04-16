package main.interop

// Equivalent to Java POJOs
// Compiler generates equals(), hashCode(), getters, setters (if mutable), copy() and toString()
data class Person(var name: String?, var age: Int, var address: String?)
