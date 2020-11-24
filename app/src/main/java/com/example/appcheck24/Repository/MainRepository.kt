package com.example.appcheck24.Repository

import com.example.appcheck24.Network.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getProducts() = apiHelper.getProducts()
}