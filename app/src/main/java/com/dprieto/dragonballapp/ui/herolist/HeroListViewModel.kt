package com.dprieto.dragonballapp.ui.herolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dprieto.dragonballapp.data.Repository
import com.dprieto.dragonballapp.domain.HeroModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    //Ejemplo de errores
    private val _state = MutableLiveData<HeroListState>()
    //Una parte publica para leer desde la vista. Se sobrescribe el get para obtener valores
    val state: LiveData<HeroListState>
        get() = _state

    companion object{
        private val TAG = "HeroListViewModel: "
    }

    fun getHeroList(){

        viewModelScope.launch {
            val heros = withContext(Dispatchers.IO){
                repository.getHeros()
            }
            _state.value = heros
        }

    }

    fun filterFavorite(heros: List<HeroModel>) {
        _state.value = HeroListState.FilterFavorites(heros)
    }

    fun unFilterFavorite(heros: List<HeroModel>) {
        _state.value = HeroListState.Success(heros)
    }
}