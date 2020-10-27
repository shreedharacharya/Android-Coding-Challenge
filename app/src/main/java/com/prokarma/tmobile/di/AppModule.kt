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

package com.prokarma.tmobile.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.prokarma.tmobile.api.OffersApi
import com.prokarma.tmobile.data.DefaultOfferRepository
import com.prokarma.tmobile.data.OfferRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun providesSchoolApi(): OffersApi {
        return OffersApi.create()
    }

    @Singleton
    @Provides
    fun provideSchoolDataRepository(
        schoolApi: OffersApi,
        preferences: SharedPreferences
    ): OfferRepository {
        return DefaultOfferRepository(schoolApi, preferences)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("tm-pref", Context.MODE_PRIVATE)
    }
}