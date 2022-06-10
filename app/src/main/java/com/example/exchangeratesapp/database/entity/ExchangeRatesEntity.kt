package com.example.exchangeratesapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.exchangeratesapp.model.Rates

@Entity(tableName = "exchange_rates")
data class ExchangeRatesEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0,
    @ColumnInfo(name = "base")
    val base: String,
    @ColumnInfo(name ="date")
    val date: String,
    @Embedded
    val rates : Rates,
    @ColumnInfo(name ="success")
    val success: Boolean,
    @ColumnInfo(name ="timestamp")
    val timestamp: Int,
    @ColumnInfo(name = "favourites")
    val isFavourite : Boolean = false
)

