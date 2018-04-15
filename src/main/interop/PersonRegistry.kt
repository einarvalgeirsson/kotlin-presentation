package main.interop

class PersonRegistry {
    val persons: MutableList<Person> = ArrayList()

    init {
        persons.add(Person("Jay", 20, "Malmö"))
    }
}