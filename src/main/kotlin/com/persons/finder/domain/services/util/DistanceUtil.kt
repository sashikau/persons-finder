package com.persons.finder.domain.services.util

import com.persons.finder.domain.data.Location
import org.springframework.stereotype.Component
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@Component
class DistanceUtil {

    fun getLocationWrapper(referenceLocation: Location, targetLocation: Location, radius: Double): LocationWrapper {
        val distance = haversine(referenceLocation.latitude, referenceLocation.longitude, targetLocation.latitude, targetLocation.longitude)
        return LocationWrapper(distance, distance <= radius, targetLocation)
    }

    internal fun haversine(
            latitude1: Double, longitude1: Double,
            latitude2: Double, longitude2: Double
    ): Double {
        val earthRadiusKm = 6371.0

        val deltaLatitude = Math.toRadians(latitude2 - latitude1)
        val deltaLongitude = Math.toRadians(longitude2 - longitude1)

        val haversineComponent = sin(deltaLatitude / 2) * sin(deltaLatitude / 2) +
                cos(Math.toRadians(latitude1)) * cos(Math.toRadians(latitude2)) *
                sin(deltaLongitude / 2) * sin(deltaLongitude / 2)

        val centralAngle = 2 * atan2(sqrt(haversineComponent), sqrt(1 - haversineComponent))

        return earthRadiusKm * centralAngle
    }

}
