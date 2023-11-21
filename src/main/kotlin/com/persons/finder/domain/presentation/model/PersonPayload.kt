package com.persons.finder.domain.presentation.model

import com.fasterxml.jackson.annotation.JsonProperty

data class PersonPayload(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        val id: Long?,
        val name: String
)