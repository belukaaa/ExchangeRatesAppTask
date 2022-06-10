package com.example.exchangeratesapp.api

import com.example.exchangeratesapp.utils.constants
import com.example.exchangeratesapp.model.ExchangeRates
import com.example.exchangeratesapp.model.LatestExchangeRates
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(constants.ENDPOINT)
    suspend fun getRates(@Query ("access_key") accessKey : String) : Response<ExchangeRates>

    @GET(constants.ENDPOINT)
    suspend fun getLatestRates(@Query("access_key") accessKey : String , @Query("symbols") symbols : String) : Response<LatestExchangeRates>
}