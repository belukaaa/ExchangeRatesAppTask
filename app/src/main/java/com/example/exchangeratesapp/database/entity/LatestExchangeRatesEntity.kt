package com.example.exchangeratesapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "latest_exchange_rates")
data class LatestExchangeRatesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0,
    @ColumnInfo(name = "rateName")
    val rateName : String,
    @ColumnInfo(name = "rateValue")
    val rateValue : Double,
    @ColumnInfo(name = "favourite")
    val isFavourite : Boolean = false
)