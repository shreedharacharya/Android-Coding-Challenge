package com.prokarma.tmobile.ui.offers

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.prokarma.tmobile.domain.offers.LoadOffersUseCase
import com.prokarma.tmobile.result.Result
import com.prokarma.tmobile.result.exception
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
class OffersViewModel @ViewModelInject constructor(
    private val loadSchoolUseCase: LoadOffersUseCase
) : ViewModel() {

    private val _offerData = MutableLiveData<Any>()
    val offerData: LiveData<Any> = _offerData
    private val reload = MutableLiveData<Boolean>()
    private val offerDataFlow: Flow<Result<Any>> = flowOf(
        reload.asFlow().flatMapLatest { loadSchoolUseCase() }
    ).flattenConcat()

    init {
        reload.value = true
        loadOfferData()
    }

    fun retry() {
        reload.value = true
    }

    private fun loadOfferData() {
        viewModelScope.launch {
            offerDataFlow.collectLatest {
                _offerData.value = when (it) {
                    is Result.Loading -> LoadingIndicator
                    is Result.Success -> it.data
                    else -> {
                        Timber.e(it.exception)
                        OffersEmpty(it.exception?.message ?: "")
                    }
                }
            }
        }
    }
}