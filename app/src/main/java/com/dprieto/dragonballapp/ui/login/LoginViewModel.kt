package com.dprieto.dragonballapp.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dprieto.dragonballapp.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository): ViewModel() {


    companion object{
        private val TAG = "LoginViewModel: "
    }

    fun doLogin(user: String, pass: String){
        viewModelScope.launch {
            val login = withContext(Dispatchers.IO){
                repository.doLogin(user, pass)
            }
            Log.d(TAG, login.toString())
        }
    }
}