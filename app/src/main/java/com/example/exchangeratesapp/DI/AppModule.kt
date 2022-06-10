package com.example.exchangeratesapp.DI

import android.app.Application
import com.example.exchangeratesapp.api.ApiService
import com.example.exchangeratesapp.utils.constants
import com.example.exchangeratesapp.database.ExchangeRatesDB
import com.example.exchangeratesapp.database.dao.ExchangeRatesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = constants.BASE_URL


    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL : String) : ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun getDatabase(context: Application) : ExchangeRatesDB{
        return ExchangeRatesDB.getDataBase(context)
    }

    @Provides
    @Singleton
    fun getDao(database : ExchangeRatesDB) : ExchangeRatesDao{
        return database.getDao()
    }

}