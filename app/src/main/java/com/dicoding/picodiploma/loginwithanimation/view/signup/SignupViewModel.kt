package com.dicoding.picodiploma.loginwithanimation.view.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.loginwithanimation.data.repository.AuthRepository
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.SignupResponse
import kotlinx.coroutines.launch

class SignupViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _signupResponse = MutableLiveData<SignupResponse>()
    val signupResponse: LiveData<SignupResponse> = _signupResponse

    fun signup(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authRepository.signup(name, email, password)
                _signupResponse.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                _signupResponse.value =
                    SignupResponse(error = true, message = e.message ?: "Unknown error")
            }
        }
    }
}

