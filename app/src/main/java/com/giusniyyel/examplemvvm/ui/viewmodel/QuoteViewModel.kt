package com.giusniyyel.examplemvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giusniyyel.examplemvvm.domain.GetQuotesUseCase
import com.giusniyyel.examplemvvm.domain.GetRandomQuotesUseCase
import com.giusniyyel.examplemvvm.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuotesUseCase: GetRandomQuotesUseCase
) : ViewModel() {
    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()

            if (result.isNotEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val quote = getRandomQuotesUseCase()
            if (quote != null) {
                quoteModel.postValue(quote) // This error is common but it works
            }
            isLoading.postValue(false)
        }
    }

}