package com.persons.finder.domain.services

import com.persons.finder.domain.data.Location
import com.persons.finder.domain.entity.PersonEntity
import com.persons.finder.domain.repository.LocationRepository
import com.persons.finder.domain.services.mapper.LocationEntityMapper
import com.persons.finder.domain.services.util.DistanceUtil
import com.persons.finder.domain.services.util.LocationWrapper
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
class LocationsServiceImpl(
        private val locationRepository: LocationRepository,
        private val locationEntityMapper: LocationEntityMapper,
        private val messageSource: MessageSource,
        private val distanceUtil: DistanceUtil
) : LocationsService {

    override fun addLocation(location: Location) {
        val locationEntity = locationEntityMapper.toEntity(location)
        locationRepository.save(locationEntity)
    }

    override fun removeLocation(locationReferenceId: Long) {
        val locationEntity = locationRepository.findByPerson(PersonEntity(locationReferenceId))
                ?: throw EntityNotFoundException(messageSource.getMessage("location.not.found", arrayOf(locationReferenceId), Locale.getDefault()))

        locationRepository.delete(locationEntity)
    }


    override fun findAround(latitude: Double, longitude: Double, radiusInKm: Double): List<Location> {
        val referencePoint = Location(0, latitude, longitude)
        val locEntities = locationRepository.findAll();
        val locationWrappers = mutableListOf<LocationWrapper>()

        for (locationEntity in locEntities) {
            val entity = locationEntityMapper.toData(locationEntity)
            val locationWrapper = distanceUtil.getLocationWrapper(referencePoint, entity, radiusInKm)
            if(locationWrapper.isWithinRadius)
            {
                locationWrappers.add(locationWrapper)
            }
        }

        return locationWrappers.sortedBy { it.distance }.map { it.location }
    }


}