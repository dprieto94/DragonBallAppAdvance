package com.dprieto.dragonballapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dprieto.dragonballapp.data.Repository
import com.dprieto.dragonballapp.domain.LoginCredentialsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _state = MutableLiveData<LoginState>()
    val state: LiveData<LoginState>
        get() = _state

    fun doLogin(){
        viewModelScope.launch {
            val login = withContext(Dispatchers.IO){
                repository.doLogin()
            }
            _state.value = login
        }
    }

    fun resetState(){
        _state.value = LoginState.WaitingInput
    }

    fun saveCredentials(user: String, pass: String){
        repository.saveParam("user", user)
        repository.saveParam("pass", pass)
    }

    fun saveToken(token: String){
        repository.saveParam("token", token)
    }

    fun preLoadCredentials(): LoginCredentialsModel{
        return LoginCredentialsModel(
            repository.getParam("user"),
            repository.getParam("pass")
        )
    }

}