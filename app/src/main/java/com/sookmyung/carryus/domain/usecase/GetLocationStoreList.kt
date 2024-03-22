package com.sookmyung.carryus.domain.usecase

import com.sookmyung.carryus.domain.repository.MainRepository
import javax.inject.Inject

class GetLocationStoreList @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double) =
        mainRepository.getLocationStoreList(latitude, longitude)
}
