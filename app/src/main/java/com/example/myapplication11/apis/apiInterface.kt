package com.example.myapplication11.apis

import com.example.myapplication11.models.MarketModel
import retrofit2.Response
import retrofit2.http.GET

interface apiInterface {
    @GET("data-api/v3/cryptocurrency/listing?start=1&limit=500")
   suspend fun getMarketData() : Response<MarketModel>
}