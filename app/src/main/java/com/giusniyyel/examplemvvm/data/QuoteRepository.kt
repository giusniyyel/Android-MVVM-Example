package com.giusniyyel.examplemvvm.data

import com.giusniyyel.examplemvvm.data.database.dao.QuoteDao
import com.giusniyyel.examplemvvm.data.database.entities.QuoteEntity
import com.giusniyyel.examplemvvm.data.model.QuoteModel
import com.giusniyyel.examplemvvm.data.network.QuoteService
import com.giusniyyel.examplemvvm.domain.model.Quote
import com.giusniyyel.examplemvvm.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {
    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response: List<QuoteModel> = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote> {
        val response: List<QuoteEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>) {
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }
}