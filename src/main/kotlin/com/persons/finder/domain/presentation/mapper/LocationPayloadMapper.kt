package com.persons.finder.domain.presentation.mapper

import com.persons.finder.domain.data.Location
import com.persons.finder.domain.presentation.model.LocationPayload
import org.springframework.stereotype.Component

@Component
class LocationPayloadMapper {

    fun map(locationRequest: LocationPayload, personId: Long): Location {
        return Location(
                latitude = locationRequest.latitude,
                longitude = locationRequest.longitude,
                referenceId = personId
        )
    }
}