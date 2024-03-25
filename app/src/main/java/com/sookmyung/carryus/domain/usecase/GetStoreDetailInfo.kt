package com.sookmyung.carryus.domain.usecase

import com.sookmyung.carryus.domain.repository.StoresRepository
import javax.inject.Inject

class GetStoreDetailInfo @Inject constructor(
    private val storesRepository: StoresRepository
) {
    suspend operator fun invoke(storeId: Int) =
        storesRepository.getStoreDetailInfo(storeId)
}
