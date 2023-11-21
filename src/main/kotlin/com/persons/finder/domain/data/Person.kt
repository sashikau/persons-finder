package com.persons.finder.domain.data

data class Person(
    val id: Long,
    val name: String
)
{
    constructor(name: String) : this(0, name)
}
