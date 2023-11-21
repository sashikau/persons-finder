package com.persons.finder.domain.presentation

import com.persons.finder.domain.data.Person
import com.persons.finder.domain.presentation.mapper.LocationPayloadMapper
import com.persons.finder.domain.presentation.model.LocationPayload
import com.persons.finder.domain.presentation.model.PersonPayload
import com.persons.finder.domain.presentation.mapper.PersonPayloadMapper
import com.persons.finder.domain.services.LocationsService
import com.persons.finder.domain.services.PersonsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/persons")
class PersonController @Autowired constructor(
        private val personsService: PersonsService,
        private val locationsService: LocationsService,
        private val locationPayloadMapper: LocationPayloadMapper,
        private val personPayloadMapper: PersonPayloadMapper
) {

    @PutMapping("/{personId}/location")
    fun updateOrAddLocation(
            @PathVariable personId: Long,
            @RequestBody locationPayload: LocationPayload
    ) {
        locationsService.addLocation(locationPayloadMapper.map(locationPayload, personId))
    }

    @PostMapping("")
    fun createPerson(@RequestBody personPayload: PersonPayload): Long {
        var person: Person = personsService.save(personPayloadMapper.map(personPayload))
        return person.id
    }

    @GetMapping("/around")
    fun getPeopleAround(
            @RequestParam latitude: Double,
            @RequestParam longitude: Double,
            @RequestParam radiusInKm: Double
    ): List<Long> {
        val locationsAround = locationsService.findAround(latitude, longitude, radiusInKm)
        return locationsAround.map { it.referenceId }
    }

    @GetMapping("/names")
    fun getPeopleNames(@RequestParam personIds: List<Long>): List<String> {
        return personIds.map { personsService.getById(it).name }
    }

    @GetMapping("")
    fun getExample(): String {
        return "Hello Example"
    }
}