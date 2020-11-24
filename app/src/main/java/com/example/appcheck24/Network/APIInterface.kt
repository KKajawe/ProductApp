package com.example.appcheck24.Network

import retrofit2.http.GET

interface ApiService {

    @GET("products-test.json")
    suspend fun getProducts(): List<Product>

}