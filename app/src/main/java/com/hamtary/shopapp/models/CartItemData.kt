package com.hamtary.shopapp.models

import com.hamtary.shopapp.models.CartItem

data class CartItemData(
    val cart_items: List<CartItem>?=null,
    val cartItem: CartItem?=null,
    val sub_total: Double,
    val total: Double
)

//AuthResponse<CartItemData>