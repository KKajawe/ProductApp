package com.example.appcheck24

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcheck24.Network.ApiHelper
import com.example.appcheck24.Network.Product
import com.example.appcheck24.Network.RetrofitBuilder
import com.example.appcheck24.Util.CustomDialogClass
import com.example.appcheck24.Util.Status
import com.example.appcheck24.ViewModel.MainViewModel
import com.example.appcheck24.ViewModel.ViewModelFactory
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONArray


class MainActivity : AppCompatActivity() {

    private var progressBar: ProgressBar? = null
    private lateinit var adapter: MainAdapter
    private var recyclerView: RecyclerView? = null
    private lateinit var viewModel: MainViewModel
    private lateinit var dialog: CustomDialogClass
    private val networkMonitor = NetworkMonitorUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setupViewModel()
        setupUI()
        setupObservers()
        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        dialog.showDialog("connected to internet")
                        /*     when (type) {
                      ConnectionType.Wifi -> {

                                       }
                                 ConnectionType.Cellular -> {
                                     dialog.showDialog("connected to cellular")
                                 }
                                 else -> { }
                             }*/

                    }
                    false -> {
                        dialog.showDialog("No Internate")
                    }
                }
            }
        }
    }

    private fun init() {
        dialog = CustomDialogClass(this)
        recyclerView = findViewById<RecyclerView>(R.id.lst_Products)
        progressBar = findViewById<ProgressBar>(R.id.dwnld_progress)
    }

    private fun setupUI() {
        recyclerView?.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                recyclerView?.context,
                (recyclerView?.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView?.adapter = adapter
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.getProducts().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView?.visibility = View.VISIBLE
                        progressBar?.visibility = View.GONE
                        var gson = Gson()
                        val weatherList: List<Product> = gson.fromJson(stringReader , Array<Product>::class.java).toList()
                        val jsonArray = JSONArray(jsonObj)
                        resource.data?.let { products -> retrieveList(products) }
                    }
                    Status.ERROR -> {
                        recyclerView?.visibility = View.VISIBLE
                        progressBar?.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar?.visibility = View.VISIBLE
                        recyclerView?.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(products: List<Product>) {
        adapter.apply {
            addProducts(products)
            notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }
}



