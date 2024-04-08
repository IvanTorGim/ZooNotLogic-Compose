package com.ivtogi.zoonotlogic.data.mapper

import com.ivtogi.zoonotlogic.data.remote.dto.ProductResponse
import com.ivtogi.zoonotlogic.domain.model.Product

fun ProductResponse.toDomain(): Product {
    return Product(
        name = this.name,
        description = this.description,
        category = this.category,
        price = this.price,
        images = this.images
    )
}