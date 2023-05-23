package com.hamtary.shopapp.repository

import com.hamtary.shopapp.data.network.ApiService
import com.hamtary.shopapp.models.AuthResponse
import com.hamtary.shopapp.models.LoginResponse
import com.hamtary.shopapp.models.Profile
import com.hamtary.shopapp.models.RegisterResponse
import com.hamtary.shopapp.models.User
import com.hamtary.shopapp.utils.Resource
import com.hamtary.shopapp.utils.Utils.tryCatch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : AuthRepository {
    override suspend fun registerUser(
        name: String,
        phone: String,
        email: String,
        password: String,
        image: String,
    ): Resource<AuthResponse<RegisterResponse>> =
        withContext(Dispatchers.IO) {
            tryCatch {
                val user = User(  name = name,
                    phone = phone,
                    email = email,
                    password = password,
                    image = image
                )
                val result = apiService.registerUser(user)
                Resource.Success(result)
            }
        }


    override suspend fun loginUser(
        email: String,
        password: String
    ): Resource<AuthResponse<LoginResponse>> =
        withContext(Dispatchers.IO) {
            tryCatch {
                val loginData = HashMap<String, String>()
                loginData["email"] = email
                loginData["password"] = password
                val result = apiService.loginUser(loginData)
                Resource.Success(result)
            }
        }

    override suspend fun getUserProfileInfo(): Resource<AuthResponse<Profile>> =
        withContext(Dispatchers.IO){
            tryCatch {
                val result = apiService.getUserProfileInfo()
                Resource.Success(result)
            }
        }

}