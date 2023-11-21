package com.persons.finder.domain.services.util

import com.persons.finder.domain.data.Location
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DistanceUtilTest {

    @Autowired
    private lateinit var distanceUtil: DistanceUtil

    @Test
    fun testGetLocationWrapper() {
        val referenceLocation = Location(1L, 0.0, 0.0)
        val targetLocationInsideRadius = Location(2L, 0.1, 0.1)
        val targetLocationOutsideRadius = Location(3L, 5.0, 1.0)
        val radius = 200.0

        val resultInsideRadius = distanceUtil.getLocationWrapper(referenceLocation, targetLocationInsideRadius, radius)
        val resultOutsideRadius = distanceUtil.getLocationWrapper(referenceLocation, targetLocationOutsideRadius, radius)

        assertEquals(targetLocationInsideRadius, resultInsideRadius.location)
        assertEquals(true, resultInsideRadius.isWithinRadius)

        assertEquals(targetLocationOutsideRadius, resultOutsideRadius.location)
        assertEquals(false, resultOutsideRadius.isWithinRadius)
    }
}

