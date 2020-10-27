package com.prokarma.tmobile.domain.offers

import com.prokarma.tmobile.data.OfferRepository
import com.prokarma.tmobile.data.Offers
import com.prokarma.tmobile.di.IoDispatcher
import com.prokarma.tmobile.domain.FlowUseCase
import com.prokarma.tmobile.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Loads the Offer data.
 */
class LoadOffersUseCase @Inject constructor(
    private val repository: OfferRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Offers>(ioDispatcher) {

    override fun execute(): Flow<Result<Offers>> =
        repository.getOfferData()
}