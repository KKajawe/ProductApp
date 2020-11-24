package com.example.appcheck24.Network

data class ProductList (var header : Headers , var filters : Filters , var products : Product)

data class Headers(var headerTitle : String,var headerDescription : String)

data class Filters()

