package com.sookmyung.carryus.domain.usecase

import com.sookmyung.carryus.domain.repository.SearchRepository
import javax.inject.Inject

class GetCityBaseStoreListUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(word: String) =
        searchRepository.getCityBaseStoreList(word)
}
