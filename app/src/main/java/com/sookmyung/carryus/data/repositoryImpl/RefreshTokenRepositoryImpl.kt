package com.sookmyung.carryus.data.repositoryImpl

import com.sookmyung.carryus.data.source.LocalDataSource
import com.sookmyung.carryus.data.source.RefreshTokenDataSource
import com.sookmyung.carryus.domain.entity.Token
import com.sookmyung.carryus.domain.repository.RefreshTokenRepository
import javax.inject.Inject

class RefreshTokenRepositoryImpl @Inject constructor(
    private val refreshTokenDataSource: RefreshTokenDataSource,
    private val localDataSource: LocalDataSource
) : RefreshTokenRepository {
    override suspend fun reissueToken(): Result<Token> =
        kotlin.runCatching { refreshTokenDataSource.reissueToken(localDataSource.accessToken, localDataSource.refreshToken) }.map { response ->
            requireNotNull(response.data).toToken()
        }

    override fun setAccessToken(accessToken: String) {
        localDataSource.accessToken = accessToken
    }
}
