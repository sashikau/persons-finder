package com.persons.finder.domain.presentation.mapper

import com.persons.finder.domain.data.Person
import com.persons.finder.domain.presentation.model.PersonPayload
import org.springframework.stereotype.Component

@Component
class PersonPayloadMapper {

    fun map(personPayload: PersonPayload): Person {
        return Person(
                name = personPayload.name
        )
    }
}