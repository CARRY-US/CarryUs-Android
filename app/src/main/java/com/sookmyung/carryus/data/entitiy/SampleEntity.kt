package com.sookmyung.carryus.data.entitiy

import com.sookmyung.carryus.domain.entity.Sample
import kotlinx.serialization.Serializable

@Serializable
data class SampleEntity(
    val name: String
) {
    fun toSample(): Sample =
        Sample(
            name = this.name
        )
}
