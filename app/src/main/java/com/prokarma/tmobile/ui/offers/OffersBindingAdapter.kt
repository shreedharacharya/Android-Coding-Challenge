package com.prokarma.tmobile.ui.offers

import android.graphics.Color
import android.util.DisplayMetrics
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.prokarma.tmobile.data.*


@BindingAdapter(value = ["offerItems"])
fun offerItems(recyclerView: RecyclerView, offerData: Any?) {
    val offerItems = when (offerData) {
        is Offers -> offerData.page.cards
        is OffersEmpty -> listOf(offerData)
        else -> listOf(LoadingIndicator)
    }

    (recyclerView.adapter as OffersAdapter).submitList(offerItems)
}

@BindingAdapter("imageFromUrl")
fun imageFromUrl(view: ImageView, image: Image) {
//    <!--newHeightOfImage= ScreenWidth*ImageOriginalHeight/ImageOriginalWidth=-->
//    <!-- image width= screen width-->

    val displayMetrics = DisplayMetrics()
    (view.context as AppCompatActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
    val height = displayMetrics.heightPixels
    val width = displayMetrics.widthPixels

    view.requestLayout()
    view.layoutParams.height = getImageNewHeight(image.size.width, image.size.height, width)
    view.layoutParams.width = width
    Glide.with(view.context as AppCompatActivity)
        .load(image.url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)


}

@BindingAdapter(value = ["text"])
fun text(textView: TextView, card: Card) {
    textView.text = card.value
    textView.textSize = (card.attributes.font.size).toFloat()
    textView.setTextColor(Color.parseColor(card.attributes.textColor))
}

@BindingAdapter(value = ["titleText"])
fun titleText(textView: TextView, title: Title) {
    textView.text = title.value
    textView.textSize = (title.attributes.font.size).toFloat()
    textView.setTextColor(Color.parseColor(title.attributes.textColor))
}

@BindingAdapter(value = ["descriptionText"])
fun descriptionText(textView: TextView, description: Description) {
    textView.text = description.value
    textView.textSize = (description.attributes.font.size).toFloat()
    textView.setTextColor(Color.parseColor(description.attributes.textColor))
}

fun getImageNewHeight(iWidth: Int, iHeight: Int, sWidth: Int): Int {
    return (sWidth * iHeight) / iWidth
}