package com.dprieto.dragonballapp.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dprieto.dragonballapp.data.Repository
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

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var loginViewModel: LoginViewModel

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
    fun `WHEN doLogin EXPECTS success returns token`() = runTest {

        //GIVEN
        repository = mockk()
        loginViewModel = LoginViewModel(repository)

        coEvery { repository.doLogin() } returns generateLoginState()

        //WHEN
        loginViewModel.doLogin()
        val actualLiveData = loginViewModel.state.getOrAwaitValue()

        //THEN
        //Se necesita ver el contenido del live data debido a que trabaja con estados y no se pueden comprobar en el assert
        //Por lo tanto, se verifica el contenido del toString que contiene el estado y el valor
        Truth.assertThat(actualLiveData.toString()).contains("token")

    }
}