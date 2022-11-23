package com.example.myapplication11.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilites {
   fun getInstance():Retrofit{
       return Retrofit.Builder()
           .baseUrl("https://api.coinmarketcap.com/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()
   }

}