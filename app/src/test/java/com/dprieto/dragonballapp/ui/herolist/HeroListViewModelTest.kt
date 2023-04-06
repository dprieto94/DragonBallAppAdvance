package com.dprieto.dragonballapp.ui.herolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dprieto.dragonballapp.data.Repository
import com.dprieto.dragonballapp.utils.generateHeroListState
import com.dprieto.dragonballapp.utils.generateLoginState
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

class HeroListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var heroListViewModel: HeroListViewModel

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
    fun `WHEN getHeros EXPECTS success returns state live data`() = runTest {

        //GIVEN
        repository = mockk()
        heroListViewModel = HeroListViewModel(repository)

        coEvery { repository.getHeros() } returns generateHeroListState()

        //WHEN
        heroListViewModel.getHeroList()
        val actualLiveData = heroListViewModel.state.getOrAwaitValue()

        //THEN
        //Se necesita ver el contenido del live data debido a que trabaja con estados y no se pueden comprobar en el assert
        //Por lo tanto, se verifica el contenido del toString que contiene el estado y el valor
        Truth.assertThat(actualLiveData.toString()).contains("heros")

    }
}