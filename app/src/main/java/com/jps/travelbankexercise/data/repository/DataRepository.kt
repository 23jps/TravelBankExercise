package com.jps.travelbankexercise.data.repository

import com.jps.travelbankexercise.data.remote.NetworkService
import com.jps.travelbankexercise.data.remote.response.TravelBankDataItem

import io.reactivex.Single
import javax.inject.Inject


class DataRepository @Inject constructor(
    private val networkService: NetworkService

) {

    fun fetchExpenseList(): Single<List<TravelBankDataItem>> {
        return networkService.doDataList(
        ).map { it }
    }

}