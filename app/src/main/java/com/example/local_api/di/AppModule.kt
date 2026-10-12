package com.example.local_api.di

import android.app.Application
import android.content.Context
import com.example.core.generalUtil.Constants
import com.example.data.interceptor.AuthInterceptor
import com.example.data.preferences.TokenManager
import com.example.data.remote.Api
import com.example.data.repository.RepositoryImpl
import com.example.domain.repository.Repository
import com.example.domain.useCases.Destroy
import com.example.domain.useCases.Index
import com.example.domain.useCases.Store
import com.example.domain.useCases.UseCasesReachPoint
import com.example.domain.useCases.Users
import com.example.domain.useCases.auth.Login
import com.example.domain.useCases.auth.Logout
import com.example.domain.useCases.auth.Register
import com.example.domain.useCases.auth.User
import com.example.domain.useCases.preferences.ClearToken
import com.example.domain.useCases.preferences.GetToken
import com.example.domain.useCases.preferences.IsLoggedIn
import com.example.domain.useCases.preferences.SaveToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOKHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }
    @Provides
    @Singleton
    fun provideLaravelApi(okHttpClient: OkHttpClient) : Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
    @Provides
    @Singleton
    fun provideTokenManager(
        @ApplicationContext context: Context
    ) : TokenManager {
        return TokenManager(context = context)
    }
    @Provides
    @Singleton
    fun provideSportsRepository(
        api: Api,
        tokenManager: TokenManager
    ) : Repository = RepositoryImpl(
        api = api,
        tokenManager = tokenManager
    )
    @Provides
    @Singleton
    fun provideSportsUseCases(repository: Repository) : UseCasesReachPoint {
        return UseCasesReachPoint(
            index = Index(repository),
            store = Store(repository),
            destroy = Destroy(repository),
            register = Register(repository),
            login = Login(repository),
            logout = Logout(repository),
            user = User(repository),
            users = Users(repository),
            saveToken = SaveToken(repository),
            getToken = GetToken(repository),
            clearToken = ClearToken(repository),
            isLoggedIn = IsLoggedIn(repository)
        )
    }
}