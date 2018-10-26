package com.ever.four.catalog.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ever.four.catalog.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

import android.widget.TextView
import com.ever.four.catalog.service.ProviderService
import com.ever.four.catalog.service.SearchService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrieveItems()
    }

    private fun retrieveItems() {
        Executors.newSingleThreadExecutor().execute({
            val json = URL("http://private-f0eea-mobilegllatam.apiary-mock.com/list").readText()
        })
    }


}
