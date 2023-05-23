package com.hamtary.shopapp.models

data class CartItem(
    val id: Int,
    val product: Product,
    var quantity: Int
)