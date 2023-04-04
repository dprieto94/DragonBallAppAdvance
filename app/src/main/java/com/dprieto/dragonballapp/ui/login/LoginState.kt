package com.dprieto.dragonballapp.ui.login

sealed class LoginState {
    data class Success(val token: String) : LoginState()
    data class Error(val error: String?) : LoginState()
    data class NetworkError(val code: Int) : LoginState()
}