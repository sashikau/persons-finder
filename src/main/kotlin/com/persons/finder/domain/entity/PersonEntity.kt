package com.persons.finder.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "person")
class PersonEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String
) {
    constructor(name: String) : this(0, name)

    constructor(id: Long) : this(id, "")

    internal constructor() : this("")
}