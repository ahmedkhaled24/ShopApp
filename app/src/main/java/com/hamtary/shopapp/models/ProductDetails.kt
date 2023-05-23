package com.hamtary.shopapp.models

import com.hamtary.shopapp.models.Product
import java.io.Serializable

data class ProductDetails (
    val product: Product,
    val category_id:Int
        ): Serializable