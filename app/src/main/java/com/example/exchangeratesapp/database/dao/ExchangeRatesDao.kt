package com.example.exchangeratesapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.exchangeratesapp.database.entity.ExchangeRatesEntity
import com.example.exchangeratesapp.database.entity.LatestExchangeRatesEntity

@Dao
interface ExchangeRatesDao {

    @Query("SELECT * FROM exchange_rates")
    fun getRates() : ExchangeRatesEntity

    @Query("SELECT * FROM latest_exchange_rates")
    fun getLatestRates() : LiveData<List<LatestExchangeRatesEntity>>

    @Query("SELECT * FROM latest_exchange_rates WHERE favourite = 1")
    fun getFavouriteRates() : LiveData<List<LatestExchangeRatesEntity>>

    @Insert
    fun insertRates(rates: ExchangeRatesEntity)

    @Insert
    fun insertLatestRates(rates : LatestExchangeRatesEntity)

    @Query("UPDATE latest_exchange_rates SET favourite = 0  WHERE id is :rateId")
    fun setToFavourite(rateId : Int)

    @Query("UPDATE latest_exchange_rates SET favourite = 1  WHERE id is :rateId")
    fun setToUnfavoriute(rateId : Int)

    @Query("SELECT * FROM latest_exchange_rates ORDER BY rateName DESC")
    fun getRatesOrderedByNameAsc() : LiveData<List<LatestExchangeRatesEntity>>

    @Query("SELECT * FROM latest_exchange_rates ORDER BY rateName ASC")
    fun getRatesOrderedByNameDesc() : LiveData<List<LatestExchangeRatesEntity>>

    @Query("SELECT * FROM latest_exchange_rates ORDER BY rateValue DESC")
    fun getRatesOrderedByValueAsc() : LiveData<List<LatestExchangeRatesEntity>>

    @Query("SELECT * FROM latest_exchange_rates ORDER BY rateValue ASC")
    fun getRatesOrderedByValueDesc() : LiveData<List<LatestExchangeRatesEntity>>

    @Query("DELETE FROM latest_exchange_rates")
    fun clearDataBase()

}