package com.sookmyung.carryus.domain.usecase

import com.sookmyung.carryus.domain.repository.MainRepository
import javax.inject.Inject

class GetUserLocationStoreList @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(xMin: Double, xMax: Double, yMin: Double, yMax: Double) =
        mainRepository.getUserLocationStoreList(xMin, xMax, yMin, yMax)
}
