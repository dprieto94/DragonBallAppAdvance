package com.dprieto.dragonballapp.data

import com.dprieto.dragonballapp.data.local.LocalDataSource
import com.dprieto.dragonballapp.data.mappers.LocalToPresentationDetailMapper
import com.dprieto.dragonballapp.data.mappers.LocalToPresentationMapper
import com.dprieto.dragonballapp.data.mappers.RemoteToLocalMapper
import com.dprieto.dragonballapp.data.mappers.ResponseToPresentationDetailMapper
import com.dprieto.dragonballapp.data.remote.RemoteDataSource
import com.dprieto.dragonballapp.fake.FakeLocalDataSource
import com.dprieto.dragonballapp.fake.FakeRemoteDataSource
import com.dprieto.dragonballapp.ui.detail.HeroDetailLocationsState
import com.dprieto.dragonballapp.ui.detail.HeroDetailState
import com.dprieto.dragonballapp.ui.herolist.HeroListState
import com.dprieto.dragonballapp.ui.login.LoginState
import com.google.common.truth.Truth
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

class RepositoryImpTest {

    //UUT o SUT
    private lateinit var repositoryImp: RepositoryImp
    private lateinit var localDataSource: LocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource


    @Before
    fun setUp() {

        localDataSource = mockk()
        remoteDataSource = mockk()

    }

    /**
     * Test Login
     */

    @Test
    fun `WHEN doLogin EXPECTS success and returns token`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("SUCCESS"),
            localDataSource,
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.doLogin()

        //THEN
        assert(actual is LoginState.Success)
        Truth.assertThat((actual as LoginState.Success).token.length).isGreaterThan(0)

    }

    @Test
    fun `WHEN doLogin EXPECTS error and returns message`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("ERROR"),
            localDataSource,
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.doLogin()

        //THEN
        assert(actual is LoginState.Error)
        Truth.assertThat((actual as LoginState.Error).error).isEqualTo("Error")

    }

    @Test
    fun `WHEN doLogin EXPECTS network error and returns code`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("NETWORK_ERROR"),
            localDataSource,
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.doLogin()

        //THEN
        assert(actual is LoginState.NetworkError)
        Truth.assertThat((actual as LoginState.NetworkError).code).isEqualTo(204)

    }


    /**
     * Test Locations
     */

    @Test
    fun `WHEN getLocations EXPECTS success and returns locations`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("SUCCESS"),
            localDataSource,
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.getLocations("")

        //THEN
        assert(actual is HeroDetailLocationsState.Success)
        Truth.assertThat((actual as HeroDetailLocationsState.Success).locations.size).isGreaterThan(0)

    }

    @Test
    fun `WHEN getLocations EXPECTS error and returns message`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("ERROR"),
            localDataSource,
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.getLocations("")

        //THEN
        assert(actual is HeroDetailLocationsState.Error)
        Truth.assertThat((actual as HeroDetailLocationsState.Error).error).isEqualTo("Error")

    }

    @Test
    fun `WHEN getLocations EXPECTS network error and returns code`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("NETWORK_ERROR"),
            localDataSource,
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.getLocations("")

        //THEN
        assert(actual is HeroDetailLocationsState.NetworkError)
        Truth.assertThat((actual as HeroDetailLocationsState.NetworkError).code).isEqualTo(204)

    }

    /**
     * Test Hero Detail
     */

    @Test
    fun `WHEN getHeroDetail EXPECTS success and returns hero`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("SUCCESS"),
            localDataSource,
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.getHeroDetail("")

        //THEN
        assert(actual is HeroDetailState.SuccessDetail)
        Truth.assertThat((actual as HeroDetailState.SuccessDetail).hero).isNotNull()

        Truth.assertThat(actual.hero.id).isEqualTo("ID: 0")
    }

    @Test
    fun `WHEN getHeroDetail EXPECTS success and returns null`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("SUCCESS_NULL"),
            localDataSource,
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.getHeroDetail("")

        //THEN
        assert(actual is HeroDetailState.Error)
    }

    @Test
    fun `WHEN getHeroDetail EXPECTS error and returns message`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("ERROR"),
            localDataSource,
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.getHeroDetail("")

        //THEN
        assert(actual is HeroDetailState.Error)
        Truth.assertThat((actual as HeroDetailState.Error).error).isEqualTo("Error")

    }

    @Test
    fun `WHEN getHeroDetail EXPECTS network error and returns code`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("NETWORK_ERROR"),
            localDataSource,
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.getHeroDetail("")

        //THEN
        assert(actual is HeroDetailState.NetworkError)
        Truth.assertThat((actual as HeroDetailState.NetworkError).code).isEqualTo(204)

    }

    /**
     * Test List of heros
     */

    @Test
    fun `WHEN getHeros EXPECTS success and returns list of heros`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("SUCCESS"),
            FakeLocalDataSource("SUCCESS"),
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.getHeros()

        //THEN
        assert(actual is HeroListState.Success)
        Truth.assertThat((actual as HeroListState.Success).heros.size).isGreaterThan(0)

        Truth.assertThat((actual).heros[0].id).isEqualTo("ID: 0")
    }

    @Test
    fun `WHEN getHeros EXPECTS success with empty local and returns heros inserted in local 0`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("SUCCESS"),
            FakeLocalDataSource("EMPTY"),
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.getHeros()

        //THEN
        assert(actual is HeroListState.Success)
        Truth.assertThat((actual as HeroListState.Success).heros.size).isEqualTo(0)
    }




    /**
     * Test Favorite
     */

    @Test
    fun `WHEN setFavorite EXPECTS success and returns nothing`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("SUCCESS"),
            FakeLocalDataSource("SUCCESS"),
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.setFavorite("", "")

        //THEN
        assert(actual is HeroDetailState.SuccessDetail)
        Truth.assertThat((actual as HeroDetailState.SuccessDetail).hero.id).isEqualTo("ID: 0")

    }

    @Test
    fun `WHEN setFavorite EXPECTS error and returns message`() = runTest {

        //GIVEN
        repositoryImp = RepositoryImp(
            FakeRemoteDataSource("ERROR"),
            localDataSource,
            LocalToPresentationDetailMapper(),
            ResponseToPresentationDetailMapper(),
            RemoteToLocalMapper(),
            LocalToPresentationMapper()
        )

        //WHEN
        val actual = repositoryImp.setFavorite("", "")

        //THEN
        assert(actual is HeroDetailState.Error)
        Truth.assertThat((actual as HeroDetailState.Error).error).isEqualTo("Error al guardar favorito")

    }


}