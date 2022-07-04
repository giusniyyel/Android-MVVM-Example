package com.giusniyyel.examplemvvm.domain

import com.giusniyyel.examplemvvm.data.QuoteRepository
import com.giusniyyel.examplemvvm.data.database.entities.toDatabase
import com.giusniyyel.examplemvvm.data.model.QuoteModel
import com.giusniyyel.examplemvvm.domain.model.Quote
import javax.inject.Inject

// GetQuotes
class GetQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(): List<Quote> {
        val quotes = repository.getAllQuotesFromApi()

        return if (quotes.isNotEmpty()) {
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        } else {
            repository.getAllQuotesFromDatabase()
        }
    }
}