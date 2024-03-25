package com.sookmyung.carryus.data.repositoryImpl

import com.sookmyung.carryus.data.source.MyDataSource
import com.sookmyung.carryus.domain.entity.MyProfile
import com.sookmyung.carryus.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
  private val myDataSource: MyDataSource
): MyRepository {
    override suspend fun getMyProfile(
    ): Result<MyProfile> = runCatching {
        myDataSource.getMyProfile()
    }.mapCatching { response ->
        response.data?.toMyProfile() ?: throw Exception("Data is null")
    }
}
