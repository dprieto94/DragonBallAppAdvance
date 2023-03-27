package com.dprieto.dragonballapp.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dprieto.dragonballapp.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: Repository): ViewModel() {


    companion object{
        private val TAG = "LoginViewModel: "
    }

    fun doLogin(){
        viewModelScope.launch {
            val login = withContext(Dispatchers.IO){
                repository.doLogin()
            }
            Log.d(TAG, login.toString())
        }
    }
}