package com.example.exchangeratesapp.model

import com.google.gson.annotations.SerializedName

data class ExchangeRates (
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: Rates? = Rates(),
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timestamp")
    val timestamp: Int
        )
