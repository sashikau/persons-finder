package com.persons.finder.domain.repository

import com.persons.finder.domain.entity.PersonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<PersonEntity, Long>