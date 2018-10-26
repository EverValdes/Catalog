package com.ever.four.catalog.service

import com.ever.four.catalog.entity.ItemEntity

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface SearchService {
    @GET("items/{id}")
    fun getProducts(@Path("id") id: String): Call<ItemEntity>

}