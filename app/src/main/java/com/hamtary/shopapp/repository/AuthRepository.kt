package com.hamtary.shopapp.repository

import com.hamtary.shopapp.models.AuthResponse
import com.hamtary.shopapp.models.LoginResponse
import com.hamtary.shopapp.models.Profile
import com.hamtary.shopapp.models.RegisterResponse
import com.hamtary.shopapp.utils.Resource

interface AuthRepository {
    suspend fun registerUser(
        name: String,
        phone: String,
        email: String,
        password: String,
        image: String
    ): Resource<AuthResponse<RegisterResponse>>

    suspend fun loginUser(
        email: String,
        password: String
    ): Resource<AuthResponse<LoginResponse>>

    suspend fun getUserProfileInfo(): Resource<AuthResponse<Profile>>
}