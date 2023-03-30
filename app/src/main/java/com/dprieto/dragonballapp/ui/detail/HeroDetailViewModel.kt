package com.dprieto.dragonballapp.ui.detail

import androidx.lifecycle.ViewModel
import com.dprieto.dragonballapp.data.Repository
import javax.inject.Inject

class HeroDetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    companion object{
        private val TAG = "HeroDetailViewModel: "
    }
}