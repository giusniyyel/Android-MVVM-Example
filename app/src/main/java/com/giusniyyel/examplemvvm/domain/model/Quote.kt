package com.giusniyyel.examplemvvm.domain.model

import com.giusniyyel.examplemvvm.data.database.entities.QuoteEntity
import com.giusniyyel.examplemvvm.data.model.QuoteModel

data class Quote(
    val quote: String,
    val author: String
)

fun QuoteModel.toDomain() = Quote(quote, author)

fun QuoteEntity.toDomain() = Quote(quote, author)
