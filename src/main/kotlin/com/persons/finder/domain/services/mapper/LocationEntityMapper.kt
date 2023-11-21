package com.persons.finder.domain.services.mapper

import com.persons.finder.domain.data.Location
import com.persons.finder.domain.entity.LocationEntity
import com.persons.finder.domain.entity.PersonEntity
import org.springframework.stereotype.Component

@Component
/**
 * Mapper class to map [LocationEntity] to [Location] and vice versa.
 */
class LocationEntityMapper {

    /**
     * Maps [LocationEntity] to [Location] data type.
     *
     * @param locationEntity The input [LocationEntity] to be mapped.
     * @return The resulting [Location] object.
     */
    fun toData(locationEntity: LocationEntity): Location {
        return Location(
                referenceId = locationEntity.person.id,
                latitude = locationEntity.latitude,
                longitude = locationEntity.longitude
        )
    }

    /**
     * Maps [Location] to a [LocationEntity] data type.
     *
     * @param location The input [Location] to be mapped.
     * @return The resulting [LocationEntity] object.
     */
    fun toEntity(location: Location): LocationEntity {
        val personEntity = PersonEntity(location.referenceId);
        return LocationEntity(personEntity, location.latitude, location.longitude)
    }
}