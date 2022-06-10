package com.example.exchangeratesapp.api

import retrofit2.Response

abstract class GenericApiCall {
        suspend fun <T: Any> apiRequest(call: suspend () -> Response<T>): T{
            val response = call.invoke()
            var error = ""
            if (response.isSuccessful){
                return response.body()!!
            }
            else{
                error = response.errorBody().toString()
                throw Exception(error)
            }
        }

}