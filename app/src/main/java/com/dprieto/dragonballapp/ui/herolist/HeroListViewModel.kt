package com.dprieto.dragonballapp.ui.herolist

import androidx.lifecycle.ViewModel
import com.dprieto.dragonballapp.data.Repository
import javax.inject.Inject

class HeroListViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    companion object{
        private val TAG = "HeroListViewModel: "
    }

    private fun getHeroList(){

    }
}