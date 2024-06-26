package com.sookmyung.carryus.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sookmyung.carryus.BuildConfig
import com.sookmyung.carryus.data.source.LocalDataSource
import com.sookmyung.carryus.domain.repository.AuthRepository
import com.sookmyung.carryus.domain.repository.RefreshTokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val json = Json { ignoreUnknownKeys = true }
    private const val CONTENT_TYPE = "Content-Type"
    private const val APPLICATION_JSON = "application/json"
    private const val BEARER = "Bearer "
    private const val AUTHORIZATION = "Authorization"
    private const val EXPIRED_TOKEN = 401

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CarryUsType

    @Provides
    @Singleton
    @CarryUsType
    fun providesCarryUsInterceptor(
        refreshTokenRepository: RefreshTokenRepository,
        localDataSource: LocalDataSource
    ): Interceptor = Interceptor { chain ->
        val request = chain.request()
        var response = chain.proceed(
            request
                .newBuilder()
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(AUTHORIZATION, BEARER + localDataSource.accessToken)
                .build()
        )

        when (response.code) {
            EXPIRED_TOKEN -> try {
                runBlocking {
                    refreshTokenRepository.reissueToken().onSuccess { accessToken ->
                        refreshTokenRepository.setAccessToken(accessToken.accessToken)
                    }
                }
                response.close()

                val newRequest = chain.request()
                var newResponse = chain.proceed(
                    newRequest
                        .newBuilder()
                        .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .addHeader(AUTHORIZATION, BEARER + localDataSource.accessToken)
                        .build()
                )
                return@Interceptor newResponse
            } catch (t: Throwable) {
                Timber.e(t.message)
            }
        }
        return@Interceptor response
    }

    @Provides
    @Singleton
    @CarryUsType
    fun providesCarryUsOkHttpClient(@CarryUsType interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }
            ).build()

    @Provides
    @Singleton
    @CarryUsType
    fun providesCarryUsRetrofit(@CarryUsType okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()
}
