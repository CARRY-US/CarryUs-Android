package com.sookmyung.carryus.data.repositoryImpl

import com.sookmyung.carryus.data.source.AuthDataSource
import com.sookmyung.carryus.data.source.LocalDataSource
import com.sookmyung.carryus.domain.entity.Token
import com.sookmyung.carryus.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localDataSource: LocalDataSource
) : AuthRepository {
    override suspend fun postLogin(platformType: String, role: String): Result<Token> =
        kotlin.runCatching { authDataSource.postLogin(platformType, role) }.map { response ->
            requireNotNull(response.data).toToken()
        }

    override fun initToken(accessToken: String, refreshToken: String) {
        localDataSource.accessToken = accessToken
        localDataSource.refreshToken = refreshToken
    }

    override fun initSignUpState(isSignUpState: Boolean) {
        localDataSource.isUserSignUp = isSignUpState
    }

    override fun getSignedUp(): Boolean = localDataSource.isUserSignUp
}
