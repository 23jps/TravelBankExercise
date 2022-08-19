package com.jps.travelbankexercise.data.repository

import com.jps.travelbankexercise.data.remote.NetworkService
import com.jps.travelbankexercise.data.remote.response.TravelBankData
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataRepositoryTest{

    @Mock
    private lateinit var networkService: NetworkService

    private lateinit var dataRepository: DataRepository

    @Before
    fun setUp() {
        dataRepository = DataRepository(networkService)
    }


    @Test
    fun fetchExpenseList_requestDoDataListCall() {

        Mockito.doReturn(Single.just(TravelBankData()))
            .`when`(networkService)
            .doDataList()
        dataRepository.fetchExpenseList()
        Mockito.verify(networkService).doDataList()

    }

}