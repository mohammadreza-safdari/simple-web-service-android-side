package com.example.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.core.generalUtil.Resource
import com.example.data.local.Dao
import com.example.data.preferences.TokenManager
import com.example.data.remote.Api
import com.example.data.remote.dto.api_authentication.toAuth
import com.example.data.remote.dto.toSport
import com.example.data.remote.dto.toUser
import com.example.data.remote.dto.toUsers
import com.example.domain.model.Sport
import com.example.domain.model.SportRequest
import com.example.domain.model.User
import com.example.domain.model.Users
import com.example.domain.model.api_model.Auth
import com.example.domain.model.api_model.LoginRequest
import com.example.domain.model.api_model.RegisterRequest
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class RepositoryImpl(
    private val api: Api,
    private val tokenManager: TokenManager
) : Repository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun index(): Flow<Resource<List<Sport>>> {
        return flow {
            emit(Resource.Loading<List<Sport>>(isLoading = true))
            val sports = try {
                api.index().map( transform = { sportResponse ->
                    sportResponse.toSport()
                })
            } catch (error: HttpException) {
                emit(Resource.Error<List<Sport>>(message = error.message))
                emit(Resource.Loading<List<Sport>>(isLoading = false))
                null
            } catch (error: IOException) {
                emit(Resource.Error<List<Sport>>(message = error.message))
                emit(Resource.Loading<List<Sport>>(isLoading = false))
                null
            }
            sports?.let {
                emit(Resource.Success<List<Sport>>(data = sports))
                emit(Resource.Loading<List<Sport>>(isLoading = false))
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun users(): Flow<Resource<List<Users>>> {
        return flow {
            emit(Resource.Loading<List<Users>>(isLoading = true))
            val users = try {
                api.users().map( transform = { usersResponse ->
                    usersResponse.toUsers()
                })
            } catch (error: HttpException) {
                emit(Resource.Error<List<Users>>(message = error.message))
                emit(Resource.Loading<List<Users>>(isLoading = false))
                null
            } catch (error: IOException) {
                emit(Resource.Error<List<Users>>(message = error.message))
                emit(Resource.Loading<List<Users>>(isLoading = false))
                null
            }
            users?.let {
                emit(Resource.Success<List<Users>>(data = users))
                emit(Resource.Loading<List<Users>>(isLoading = false))
            }
        }
    }

    override suspend fun store(request: SportRequest): Result<List<Sport>> {
        val response = api.store(request)
        return runCatching {
            response.map {
                it.toSport()
            }
        }
    }

    override suspend fun destroy(sportId: Int) {
        val response = api.destroy(sportId)
    }

    override suspend fun register(request: RegisterRequest): Result<Auth> {
        return runCatching { //manage exceptions
            api.register(request).toAuth()
        }
    }

    override suspend fun login(request: LoginRequest): Result<Auth> {
        return runCatching {
            api.login(request).toAuth()
        }
    }

    override suspend fun logout(): Result<Unit> {
        return runCatching {
            api.logout("")
        }
    }

    override suspend fun user(): Result<User> {
        return runCatching {
            api.user("").toUser()
        }
    }

    override suspend fun saveToken(token: String) {
        tokenManager.saveToken(token)
    }

    override suspend fun getToken(): String? {
        return tokenManager.getToken()
    }

    override suspend fun clearToken() {
        tokenManager.clearToken()
    }

    override suspend fun isLoggedIn(): Boolean {
        return tokenManager.isLoggedIn()
    }
}