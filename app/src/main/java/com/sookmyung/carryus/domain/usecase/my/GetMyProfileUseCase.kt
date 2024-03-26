package com.sookmyung.carryus.domain.usecase.my

import com.sookmyung.carryus.domain.repository.MyRepository
import javax.inject.Inject

class GetMyProfileUseCase @Inject constructor(
    private val myRepository: MyRepository
){
    suspend operator fun invoke() =
        myRepository.getMyProfile()
}
