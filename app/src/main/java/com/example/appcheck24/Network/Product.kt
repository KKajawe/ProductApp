package com.example.appcheck24.Network

import com.google.gson.annotations.SerializedName

data class Product( @SerializedName("products") var productItem : ProductItem)

data class ProductItem (var id: Int, var name: String, var imageURL: String, var available: Boolean,
                        var description: String, var rating: String, var price: Price, var longDescription: String,
                        var releaseDate: String, var colorCode: String,var type : String)

data class Price(var value: Float, var Currency: String)