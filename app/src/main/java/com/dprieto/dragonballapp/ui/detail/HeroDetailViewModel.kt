package com.dprieto.dragonballapp.ui.detail

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
class HeroDetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _state = MutableLiveData<HeroDetailState>()
    val state: LiveData<HeroDetailState>
        get() = _state

    fun getSuperHeroDetail(name: String){
        viewModelScope.launch {
            val heroState = withContext(Dispatchers.IO){
                repository.getHeroDetail(name)
            }

            _state.value = heroState
        }
    }

    fun updateFavorite(id: String, name: String){
        viewModelScope.launch {
            val heroState = withContext(Dispatchers.IO){
                repository.setFavorite(id, name)
            }

            _state.value = heroState
        }
    }

    fun getLocations(id: String){
        viewModelScope.launch {
            val heroLocations = withContext(Dispatchers.IO){
                repository.getLocations(id)
            }

            _state.value = heroLocations
        }
    }
}