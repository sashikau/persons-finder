package com.persons.finder.domain.services.mapper

import com.persons.finder.domain.data.Person
import com.persons.finder.domain.entity.PersonEntity
import org.springframework.stereotype.Component

@Component
class PersonEntityMapper {

    fun toData(personEntity: PersonEntity): Person {
        return Person(
                id = personEntity.id,
                name = personEntity.name
        )
    }

    fun toEntity(person: Person): PersonEntity {
        return PersonEntity(person.name)
    }
}