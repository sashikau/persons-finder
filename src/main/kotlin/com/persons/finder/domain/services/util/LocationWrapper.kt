package com.persons.finder.domain.services.util

import com.persons.finder.domain.data.Location

data class LocationWrapper
(
    val distance: Double,
    val isWithinRadius: Boolean,
    val location: Location
)