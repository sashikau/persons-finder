package com.persons.finder.domain.presentation

import com.fasterxml.jackson.databind.ObjectMapper
import com.persons.finder.domain.data.Person
import com.persons.finder.domain.presentation.mapper.LocationPayloadMapper
import com.persons.finder.domain.presentation.mapper.PersonPayloadMapper
import com.persons.finder.domain.presentation.model.LocationPayload
import com.persons.finder.domain.presentation.model.PersonPayload
import com.persons.finder.domain.services.LocationsService
import com.persons.finder.domain.services.PersonsService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doNothing
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerIntegrationTest @Autowired constructor(
        @Suppress("SpringJavaInjectionPointsAutowiringInspection") private val mockMvc: MockMvc,
        private val locationPayloadMapper: LocationPayloadMapper,
        private val personPayloadMapper: PersonPayloadMapper
) {

    @MockBean
    private lateinit var personsService: PersonsService

    @MockBean
    private lateinit var locationsService: LocationsService

    @Test
    fun testUpdateOrAddLocation() {
        doNothing().`when`(locationsService).addLocation(any())

        val personId = 1L
        val locationPayload = LocationPayload(37.7749, -122.4194)

        mockMvc.perform(
                put("/api/v1/persons/{personId}/location", personId)
                        .content(asJsonString(locationPayload))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk)

    }

    @Test
    fun testCreatePerson() {
        `when`(personsService.save(any())).thenReturn(Person(1, "John"))

        val personPayload = PersonPayload(1, "Doe")

        mockMvc.perform(
                post("/api/v1/persons")
                        .content(asJsonString(personPayload))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk)
                .andExpect(content().string("1"))

    }

    @Test
    fun testGetPeopleAround() {
        `when`(locationsService.findAround(any(), any(), any())).thenReturn(listOf())

        val latitude = 37.7749
        val longitude = -122.4194
        val radiusInKm = 10.0

        mockMvc.perform(
                get("/api/v1/persons/around")
                        .param("latitude", latitude.toString())
                        .param("longitude", longitude.toString())
                        .param("radiusInKm", radiusInKm.toString())
        )
                .andExpect(status().isOk)

    }

    @Test
    fun testGetPeopleNames() {
        `when`(personsService.getById(any())).thenReturn(Person(1, "John"))

        val personIds = listOf(1L, 2L, 3L)

        mockMvc.perform(
                get("/api/v1/persons/names")
                        .param("personIds", personIds.joinToString(","))
        )
                .andExpect(status().isOk)
                .andExpect(content().json("""["John", "John", "John"]"""))

    }

    private fun asJsonString(obj: Any): String {
        return ObjectMapper().writeValueAsString(obj)
    }
}
