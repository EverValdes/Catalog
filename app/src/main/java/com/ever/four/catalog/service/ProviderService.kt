package com.ever.four.catalog.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit instance to perform calls to the API
 */
object ProviderService {
    private lateinit var retrofit: Retrofit
    private val BASE_URL = "http://private-f0eea-mobilegllatam.apiary-mock.com/list/"

    val retrofitInstance: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}