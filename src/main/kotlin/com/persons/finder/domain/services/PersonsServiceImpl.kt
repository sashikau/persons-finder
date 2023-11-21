package com.persons.finder.domain.services

import com.persons.finder.domain.data.Person
import com.persons.finder.domain.repository.PersonRepository
import com.persons.finder.domain.services.mapper.PersonEntityMapper
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.Locale
import javax.persistence.EntityNotFoundException

@Service
class PersonsServiceImpl(private val personRepository: PersonRepository,
                         private val personEntityMapper: PersonEntityMapper,
                         private val messageSource: MessageSource) : PersonsService {

    override fun getById(id: Long): Person {
        val personEntity = personRepository.findById(id)
                .orElseThrow { EntityNotFoundException(messageSource.getMessage("person.not.found", arrayOf(id), Locale.getDefault())) }

        return personEntityMapper.toData(personEntity)
    }

    override fun save(person: Person): Person {
        val personEntity = personEntityMapper.toEntity(person)
        val savedPersonEntity = personRepository.save(personEntity)

        return personEntityMapper.toData(savedPersonEntity)
    }
}