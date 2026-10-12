package com.example.domain.useCases

import com.example.domain.useCases.auth.Login
import com.example.domain.useCases.auth.Logout
import com.example.domain.useCases.auth.Register
import com.example.domain.useCases.auth.User
import com.example.domain.useCases.preferences.ClearToken
import com.example.domain.useCases.preferences.GetToken
import com.example.domain.useCases.preferences.IsLoggedIn
import com.example.domain.useCases.preferences.SaveToken

data class UseCasesReachPoint(
    val index: Index,
    val store: Store,
    val destroy: Destroy,
    val register: Register,
    val login: Login,
    val logout: Logout,
    val user: User,
    val users: Users,
    val saveToken: SaveToken,
    val getToken: GetToken,
    val clearToken: ClearToken,
    val isLoggedIn: IsLoggedIn
)
