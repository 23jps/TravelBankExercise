package com.jps.travelbankexercise.data.remote

import com.jps.travelbankexercise.data.remote.response.TravelBankData
import io.reactivex.Single
import retrofit2.http.*


interface NetworkService {

    @GET(EndPoint.DATA_END_POINT)
    fun doDataList(): Single<TravelBankData>

}