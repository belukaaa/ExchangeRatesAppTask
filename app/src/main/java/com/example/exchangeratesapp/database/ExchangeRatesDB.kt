package com.example.exchangeratesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exchangeratesapp.database.dao.ExchangeRatesDao
import com.example.exchangeratesapp.database.entity.ExchangeRatesEntity
import com.example.exchangeratesapp.database.entity.LatestExchangeRatesEntity

//const val DATABASE_VERSION = 1

@Database(entities = [ExchangeRatesEntity::class , LatestExchangeRatesEntity::class], version = 7 )
abstract class ExchangeRatesDB : RoomDatabase(){

    abstract fun getDao() : ExchangeRatesDao
    companion object {
        private var INSTANCE : ExchangeRatesDB? = null

        fun getDataBase(context: Context) : ExchangeRatesDB {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder<ExchangeRatesDB>(
                    context.applicationContext , ExchangeRatesDB::class.java , "MYDB"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }
}