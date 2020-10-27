/*
 * Copyright 2020 Shreedhar Acharya
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prokarma.tmobile.data


import android.content.SharedPreferences
import com.google.gson.Gson
import com.prokarma.tmobile.api.OffersApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.prokarma.tmobile.result.Result

/**
 * Single point of access to offer list data for presentation layer.
 */
interface OfferRepository {
    fun getOfferData(): Flow<Result<Offers>>
}

class DefaultOfferRepository(
    private val offersApi: OffersApi,
    private val preferences: SharedPreferences
) : OfferRepository {

    /**
     * As the data is small i saved it in the SharedPreference,
     * Otherwise, i would need to save it in Room db.
     */
    override fun getOfferData(): Flow<Result<Offers>> {
        return flow {
            emit(Result.Loading)

            var offers: Offers

            // first load from service
            try {
                offers = offersApi.getOffers()
                val jsonOffers = Gson().toJson(offers, Offers::class.java)
                preferences.edit().putString("offers", jsonOffers).apply()
            } catch (e: Exception) {
                // if failed to load from service check in local.
                val offersJson = preferences.getString("offers", "") ?: ""
                if (offersJson.isNotEmpty()) {
                    offers = Gson().fromJson(offersJson, Offers::class.java)
                } else throw e
            }
            emit(Result.Success(offers))
        }
    }
}