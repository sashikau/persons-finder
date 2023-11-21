package com.persons.finder.domain.presentation.mapper

import com.persons.finder.domain.presentation.model.LocationPayload
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LocationPayloadMapperTest {

    @Test
    fun testMapWithValidData() {
        val locationPayload = LocationPayload(latitude = 37.7749, longitude = -122.4194)
        val personId = 1L

        val locationPayloadMapper = LocationPayloadMapper()
        val result = locationPayloadMapper.map(locationPayload, personId)

        assertEquals(37.7749, result.latitude)
        assertEquals(-122.4194, result.longitude)
        assertEquals(personId, result.referenceId)
    }

    @Test
    fun testMapWithZeroValues() {
        val personId = 1L
        val zero = 0.0
        val locationPayload = LocationPayload(zero,zero)
        val locationPayloadMapper = LocationPayloadMapper()
        val result = locationPayloadMapper.map(locationPayload, personId)

        assertEquals(zero, result.latitude)
        assertEquals(zero, result.longitude)
        assertEquals(personId, result.referenceId)
    }

}
