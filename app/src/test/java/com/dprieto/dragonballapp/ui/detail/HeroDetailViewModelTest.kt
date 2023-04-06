package com.dprieto.dragonballapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dprieto.dragonballapp.data.Repository
import com.dprieto.dragonballapp.ui.herolist.HeroListViewModel
import com.dprieto.dragonballapp.utils.generateHeroDetailState
import com.dprieto.dragonballapp.utils.generateHeroListState
import com.dprieto.dragonballapp.utils.generateLocationsState
import com.dprieto.dragonballapp.utils.getOrAwaitValue
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeroDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var heroDetailViewModel: HeroDetailViewModel

    private lateinit var repository: Repository

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `WHEN getHeroDetail EXPECTS success returns state live data`() = runTest {

        //GIVEN
        repository = mockk()
        heroDetailViewModel = HeroDetailViewModel(repository)

        coEvery { repository.getHeroDetail("") } returns generateHeroDetailState()

        //WHEN
        heroDetailViewModel.getSuperHeroDetail("")
        val actualLiveData = heroDetailViewModel.state.getOrAwaitValue()

        //THEN
        //Se necesita ver el contenido del live data debido a que trabaja con estados y no se pueden comprobar en el assert
        //Por lo tanto, se verifica el contenido del toString que contiene el estado y el valor
        Truth.assertThat(actualLiveData.toString()).contains("hero")

    }

    @Test
    fun `WHEN getLocations EXPECTS success returns locations state live data`() = runTest {

        //GIVEN
        repository = mockk()
        heroDetailViewModel = HeroDetailViewModel(repository)

        coEvery { repository.getLocations("") } returns generateLocationsState()

        //WHEN
        heroDetailViewModel.getLocations("")
        val actualLiveData = heroDetailViewModel.locations.getOrAwaitValue()

        //THEN
        //Se necesita ver el contenido del live data debido a que trabaja con estados y no se pueden comprobar en el assert
        //Por lo tanto, se verifica el contenido del toString que contiene el estado y el valor
        Truth.assertThat(actualLiveData.toString()).contains("locations")

    }

    @Test
    fun `WHEN updateFavorite EXPECTS success returns state live data`() = runTest {

        //GIVEN
        repository = mockk()
        heroDetailViewModel = HeroDetailViewModel(repository)

        coEvery { repository.setFavorite("", "") } returns generateHeroDetailState()

        //WHEN
        heroDetailViewModel.updateFavorite("", "")
        val actualLiveData = heroDetailViewModel.state.getOrAwaitValue()

        //THEN
        //Se necesita ver el contenido del live data debido a que trabaja con estados y no se pueden comprobar en el assert
        //Por lo tanto, se verifica el contenido del toString que contiene el estado y el valor
        Truth.assertThat(actualLiveData.toString()).contains("hero")

    }
}