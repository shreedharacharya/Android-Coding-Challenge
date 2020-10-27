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

package com.prokarma.tmobile.ui.offers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.prokarma.tmobile.BR
import com.prokarma.tmobile.R
import com.prokarma.tmobile.data.Card
import com.prokarma.tmobile.data.Cards
import com.prokarma.tmobile.databinding.OffersFeedEmptyBinding


// For Offer items
class OfferViewBinder :
    FeedItemViewBinder<Cards, OffersViewHolder>(Cards::class.java) {

    override fun createViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        return OffersViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        )
    }

    override fun bindViewHolder(model: Cards, viewHolder: OffersViewHolder) {
        viewHolder.bind(offerData = model.card)
    }

    /**
     * Returns the layout based on the card-type.
     */
    override fun getFeedItemType(cardType: Any): Int {
        return when ((cardType as Cards).cardType) {
            "title_description" -> R.layout.card_title_description
            "image_title_description" -> R.layout.card_image_title_description
            else -> R.layout.card_text // "text"
        }

    }

    override fun areItemsTheSame(oldItem: Cards, newItem: Cards): Boolean =
        oldItem.cardType == newItem.cardType


    override fun areContentsTheSame(oldItem: Cards, newItem: Cards): Boolean =
        oldItem == newItem
}

class OffersViewHolder(
    private val binding: ViewDataBinding
) : ViewHolder(binding.root) {

    fun bind(offerData: Card) {
        binding.setVariable(BR.card, offerData)
        binding.executePendingBindings()
    }
}

//shown while loading
object LoadingIndicator

class LoadingViewHolder(itemView: View) : ViewHolder(itemView)

class OffersLoadingViewBinder :
    FeedItemViewBinder<LoadingIndicator, LoadingViewHolder>(LoadingIndicator::class.java) {
    override fun createViewHolder(parent: ViewGroup, viewType: Int): LoadingViewHolder {
        return LoadingViewHolder(
            LayoutInflater.from(parent.context).inflate(getFeedItemType(Any()), parent, false)
        )
    }

    override fun bindViewHolder(model: LoadingIndicator, viewHolder: LoadingViewHolder) {}

    override fun getFeedItemType(cardType: Any): Int {
        return R.layout.offer_feed_loading
    }

    override fun areItemsTheSame(oldItem: LoadingIndicator, newItem: LoadingIndicator): Boolean =
        true

    override fun areContentsTheSame(oldItem: LoadingIndicator, newItem: LoadingIndicator): Boolean =
        true
}

// Shown if no items is found or fail to load the items.
data class OffersEmpty(val message: String)

class EmptyViewHolder(
    private val binding: ViewDataBinding
) : ViewHolder(binding.root) {
    fun bind(errorMsg: String, retryCallBack: () -> Unit) {
        binding.setVariable(BR.message, errorMsg)
        (binding as OffersFeedEmptyBinding).retryButton.setOnClickListener {
            retryCallBack()
        }
        binding.executePendingBindings()
    }
}


class OffersEmptyViewBinder(private val retryCallBack: () -> Unit) :
    FeedItemViewBinder<OffersEmpty, EmptyViewHolder>(
        OffersEmpty::class.java
    ) {

    override fun createViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder {
        return EmptyViewHolder(
            OffersFeedEmptyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun bindViewHolder(model: OffersEmpty, viewHolder: EmptyViewHolder) {
        viewHolder.bind(model.message, retryCallBack)
    }

    override fun getFeedItemType(cardType: Any): Int {
        return R.layout.offers_feed_empty
    }


    override fun areItemsTheSame(oldItem: OffersEmpty, newItem: OffersEmpty) = true

    override fun areContentsTheSame(oldItem: OffersEmpty, newItem: OffersEmpty) = true
}