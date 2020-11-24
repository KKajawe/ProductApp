package com.example.appcheck24.Network

class ApiHelper(private val apiService: ApiService) {

    suspend fun getProducts() = apiService.getProducts()
}