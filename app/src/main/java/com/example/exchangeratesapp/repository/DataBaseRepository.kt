package com.example.exchangeratesapp.repository

import androidx.lifecycle.LiveData
import com.example.exchangeratesapp.database.dao.ExchangeRatesDao
import com.example.exchangeratesapp.database.entity.ExchangeRatesEntity
import com.example.exchangeratesapp.database.entity.LatestExchangeRatesEntity
import com.example.exchangeratesapp.model.ExchangeRates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DataBaseRepository @Inject constructor(private val dao: ExchangeRatesDao) {

    fun getExchangeRatesFromDB(): ExchangeRatesEntity {
        return dao.getRates()
    }

    fun saveRates(rates: ExchangeRatesEntity) {
        dao.insertRates(rates)
    }
    fun getSortedRatesByNameAsc() : LiveData<List<LatestExchangeRatesEntity>> {
        return dao.getRatesOrderedByNameAsc()
    }
    fun getSortedRatesByNameDesc() : LiveData<List<LatestExchangeRatesEntity>> {
        return dao.getRatesOrderedByNameDesc()
    }
    fun getSortedRatesByValueAsc() : LiveData<List<LatestExchangeRatesEntity>> {
        return dao.getRatesOrderedByValueAsc()
    }
    fun getSortedRatesByValueDesc() : LiveData<List<LatestExchangeRatesEntity>> {
        return dao.getRatesOrderedByValueDesc()
    }

   fun saveLatestRates(latestRates: LatestExchangeRatesEntity) {
        dao.insertLatestRates(latestRates)
    }

    fun getLatestRatesFromDB(): LiveData<List<LatestExchangeRatesEntity>> {
        return dao.getLatestRates()
    }
    fun getFavouriteRates() : LiveData<List<LatestExchangeRatesEntity>>{
        return dao.getFavouriteRates()
    }

    fun makeFavourite(rateId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.setToFavourite(rateId)
        }
    }

    fun makeUnFavourite(rateId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.setToUnfavoriute(rateId)
        }
    }

    fun clearDataBase() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.clearDataBase()
        }
    }

}