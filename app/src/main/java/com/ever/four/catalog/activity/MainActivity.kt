package com.ever.four.catalog.activity

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.ever.four.catalog.R
import java.net.URL

import com.ever.four.catalog.adapter.RecyclerAdapter
import com.ever.four.catalog.entity.ItemEntity
import com.ever.four.catalog.util.ImageRetriever
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileNotFoundException
import java.lang.Exception
import java.net.UnknownHostException
import java.util.concurrent.Executors

private const val ENDPOINT = "http://private-f0eea-mobilegllatam.apiary-mock.com/lista"
class MainActivity : AppCompatActivity() {
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        retrieveItems()
    }

    private fun retrieveItems() {
        showOverlay()
        Executors.newSingleThreadExecutor().execute() {
            var gson = Gson()
            try {
                val objectList = gson.fromJson(
                    URL(ENDPOINT).readText(),
                    Array<ItemEntity>::class.java
                ).asList()
                initializeAdapter(objectList)
            } catch (e: UnknownHostException) {
                displayNoDataAvailable(getString(R.string.no_connection))
            } catch (e: FileNotFoundException) {
                displayNoDataAvailable(getString(R.string.no_data))
            } catch (e: Exception) {
                displayNoDataAvailable(getString(R.string.unknown_error))
            }
        }

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

    private fun displayNoDataAvailable(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(getString(R.string.error))
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton(getString(R.string.retry)) {dialog, whichButton ->
            retrieveItems()
        }
        alertDialogBuilder.setNegativeButton(getString(R.string.cancel)) {dialog, whichButton ->
            android.os.Process.killProcess(android.os.Process.myPid())
        }

        runOnUiThread {
            hideOverlay()
            alertDialogBuilder.create().show()
        }
    }
}
