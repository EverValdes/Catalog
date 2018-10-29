package com.ever.four.catalog.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ever.four.catalog.R
import java.net.URL

import android.widget.TextView
import com.ever.four.catalog.adapter.RecyclerAdapter
import com.ever.four.catalog.entity.ItemEntity
import com.ever.four.catalog.service.ProviderService
import com.ever.four.catalog.service.SearchService
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager


        retrieveItems()
    }

    private fun retrieveItems(){
        showOverlay()
        Executors.newSingleThreadExecutor().execute({
            val json = URL("http://private-f0eea-mobilegllatam.apiary-mock.com/list").readText()
            var gson = Gson()
            val objectList = gson.fromJson(json, Array<ItemEntity>::class.java).asList()
            initializeAdapter(objectList)
        })

    }

    private fun initializeAdapter(objectList: List<ItemEntity>) {
        runOnUiThread {
            adapter = RecyclerAdapter(objectList)
            recycler_view.adapter = adapter
            hideOverlay()
        }

    }


    private fun showOverlay() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideOverlay() {
        progressBar.visibility = View.GONE
    }
}
