package com.dprieto.dragonballapp.ui.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dprieto.dragonballapp.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository, private val sharedPreferences: SharedPreferences): ViewModel() {

    companion object{
        private val TAG = "LoginViewModel: "
    }

    private val _state = MutableLiveData<LoginState>()
    val state: LiveData<LoginState>
        get() = _state

    fun doLogin(){
        viewModelScope.launch {
            val login = withContext(Dispatchers.IO){
                repository.doLogin()
            }
            Log.d(TAG, login.toString())
        }
    }

    fun saveCredentials(user: String, pass: String){
        sharedPreferences.edit().apply {
            putString("user", user)
            putString("pass", pass)
        }.apply()
    }

    fun saveToken(token: String){
        sharedPreferences.edit().apply {
            putString("token", token)
        }.apply()
    }

}