package com.persons.finder.domain.services

import com.persons.finder.domain.data.Person
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable

interface PersonsService {
    @Cacheable("persons")
    fun getById(id: Long): Person

    @CachePut("persons", key = "#result.id")
    fun save(person: Person): Person
}