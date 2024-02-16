package com.sookmyung.carryus.domain.usecase

import com.sookmyung.carryus.domain.repository.SampleRepository
import javax.inject.Inject

class SampleUseCase @Inject constructor(
    private val sampleRepository: SampleRepository
) {
    suspend operator fun invoke(name: String) = sampleRepository.sample(name)
}
