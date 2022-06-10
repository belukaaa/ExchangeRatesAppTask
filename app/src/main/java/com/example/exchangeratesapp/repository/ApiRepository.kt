package com.example.exchangeratesapp.repository

import com.example.exchangeratesapp.api.ApiService
import com.example.exchangeratesapp.api.GenericApiCall
import javax.inject.Inject

class ApiRepository
@Inject constructor(private val api : ApiService) : GenericApiCall()
{
    suspend fun getExchangeRates(accesKey : String) = apiRequest {
        api.getRates(accesKey)
    }
    suspend fun getLatestRates(accesKey: String , symbols : String) = apiRequest {
        api.getLatestRates(accesKey , symbols)
    }
}