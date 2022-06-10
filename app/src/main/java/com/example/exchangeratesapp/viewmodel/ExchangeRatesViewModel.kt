package com.example.exchangeratesapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangeratesapp.repository.ApiRepository
import com.example.exchangeratesapp.utils.constants.API_KEY
import com.example.exchangeratesapp.utils.constants.SYMBOLS
import com.example.exchangeratesapp.database.entity.LatestExchangeRatesEntity
import com.example.exchangeratesapp.model.ExchangeRates
import com.example.exchangeratesapp.model.LatestExchangeRates
import com.example.exchangeratesapp.repository.DataBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeRatesViewModel
@Inject constructor(private val apiRepository: ApiRepository, private val dataBaseRepository: DataBaseRepository) : ViewModel() {

    private val _response = MutableLiveData<ExchangeRates>()
    val responseRates : LiveData<ExchangeRates> get() = _response

    var sortBy = MutableLiveData("A-B")

    val rates = dataBaseRepository.getLatestRatesFromDB()
    val favouriteRates = dataBaseRepository.getFavouriteRates()
    val sortedRatesByNameAsc = dataBaseRepository.getSortedRatesByNameAsc()
    val sortedRatesByNameDesc = dataBaseRepository.getSortedRatesByNameDesc()
    val sortedRatesByValueAsc = dataBaseRepository.getSortedRatesByValueAsc()
    val sortedRatesByValueDesc = dataBaseRepository.getSortedRatesByValueDesc()


    init {
        getExchangeRates()
    }

    fun makeFavourite(rateId: Int){
            dataBaseRepository.makeFavourite(rateId)

    }
    fun makeUnFavourite(rateId: Int){
            dataBaseRepository.makeUnFavourite(rateId)

    }

    private fun getExchangeRates() = viewModelScope.launch {
        delay(2000)
        if (rates.value.isNullOrEmpty()){
            apiRepository.getLatestRates(API_KEY , SYMBOLS).let {
                if (it.success == true){
                    createRecyclerItems(it)
                }
                Log.i("requesterrorbratuxa" , "${it.rates?.javaClass?.declaredFields?.size}")
            }
        }

//        apiRepository.getExchangeRates(constants.API_KEY).let { response ->
//
//            if (response.success){
//                Log.d("requesterrorbratuxa" , "succes bratuxa ${response}")
//                _response.postValue(response)
//                val ratesForDb = ExchangeRatesEntity(
//                    base = response.base ,
//                    date = response.date,
//                    success = response.success,
//                    timestamp = response.timestamp,
//                    rates = response.rates!!
//                )
//                Log.i("requesterrorbratuxa" , "$ratesForDb" )
//
//                dataBaseRepository.saveRates(ratesForDb)
//            }else {
//                Log.d("requesterrorbratuxa" , "error message ${response}")
//            }
//
//        }
    }

    private fun createRecyclerItems(it : LatestExchangeRates) {
        val arrayOfRates = listOf("USD" , "AUD" , "CAD" , "PLN" , "MXN")
        val createRecyclerViewItems = ArrayList<LatestExchangeRatesEntity>()
        createRecyclerViewItems.add(LatestExchangeRatesEntity(rateName = arrayOfRates[0] , rateValue = it.rates?.USD!! , isFavourite = false))
        createRecyclerViewItems.add(LatestExchangeRatesEntity(rateName = arrayOfRates[1] , rateValue = it.rates?.AUD!! , isFavourite = false))
        createRecyclerViewItems.add(LatestExchangeRatesEntity(rateName = arrayOfRates[2] ,rateValue = it.rates?.CAD!! ,isFavourite =  false))
        createRecyclerViewItems.add(LatestExchangeRatesEntity(rateName = arrayOfRates[3] ,rateValue = it.rates?.PLN!! , isFavourite = false))
        createRecyclerViewItems.add(LatestExchangeRatesEntity(rateName = arrayOfRates[4] ,rateValue = it.rates?.MXN!! , isFavourite = false))

            createRecyclerViewItems.forEach{
                viewModelScope.launch(Dispatchers.IO) {
                    dataBaseRepository.saveLatestRates(it)
                }
        }

        Log.i("requesterrorbratuxa" , "$createRecyclerViewItems")
    }

    fun refreshData() {
        viewModelScope.launch {
            dataBaseRepository.clearDataBase()

            apiRepository.getLatestRates(API_KEY , SYMBOLS).let {
                if (it.success == true){
                    createRecyclerItems(it)
                }
                Log.i("requesterrorbratuxa" , "${it.rates?.javaClass?.declaredFields?.size}")
            }
        }

    }


}