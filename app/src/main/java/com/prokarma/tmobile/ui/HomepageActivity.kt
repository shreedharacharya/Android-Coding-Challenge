package com.prokarma.tmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.prokarma.tmobile.R
import com.prokarma.tmobile.data.Offers
import com.prokarma.tmobile.databinding.ActivityHomepageBinding
import com.prokarma.tmobile.ui.offers.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
@ExperimentalCoroutinesApi
@FlowPreview
class HomepageActivity : AppCompatActivity() {

    private val viewModel: OffersViewModel by viewModels()
    private lateinit var adapter: OffersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            setContentView<ActivityHomepageBinding>(this, R.layout.activity_homepage).apply {
                lifecycleOwner = this@HomepageActivity
            }
        binding.viewModel = viewModel
        adapter = createAdapter()
        binding.recyclerView.adapter = adapter
    }


    private fun createAdapter(): OffersAdapter {
        val offerBinder = OfferViewBinder()
        val emptyBinder = OffersEmptyViewBinder {
            viewModel.retry()
        }
        val loadingBinder = OffersLoadingViewBinder()


        @Suppress("UNCHECKED_CAST")
        return OffersAdapter(
            mapOf(
                Pair(R.layout.card_text, offerBinder as FeedItemBinder),
                Pair(R.layout.card_title_description, offerBinder as FeedItemBinder),
                Pair(R.layout.card_image_title_description, offerBinder as FeedItemBinder),
                Pair(R.layout.offers_feed_empty, emptyBinder as FeedItemBinder),
                Pair(R.layout.offer_feed_loading, loadingBinder as FeedItemBinder)
            ),
            mapOf(
                Pair(
                    offerBinder.modelClass,
                    offerBinder as FeedItemBinder
                ),
                Pair(
                    emptyBinder.modelClass,
                    emptyBinder as FeedItemBinder
                ),
                Pair(
                    loadingBinder.modelClass,
                    loadingBinder as FeedItemBinder
                )
            )
        )
    }
}
