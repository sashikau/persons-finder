package com.persons.finder.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name = "location")
class LocationEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @ManyToOne
        @JoinColumn(name = "person_id")
        val person: PersonEntity,

        val latitude: Double,
        val longitude: Double
)
{
        constructor(person: PersonEntity, latitude: Double, longitude: Double) : this(0, person, latitude, longitude)

        internal constructor() : this(PersonEntity(), 0.0, 0.0);
}