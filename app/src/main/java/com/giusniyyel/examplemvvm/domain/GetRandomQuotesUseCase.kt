package com.giusniyyel.examplemvvm.domain

import com.giusniyyel.examplemvvm.data.QuoteRepository
import com.giusniyyel.examplemvvm.data.model.QuoteModel
import com.giusniyyel.examplemvvm.domain.model.Quote
import javax.inject.Inject

class GetRandomQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(): Quote? {
        val quotes = repository.getAllQuotesFromDatabase()
        if (quotes.isNotEmpty()) {
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }
        return null
    }
}