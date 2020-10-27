package com.prokarma.tmobile.data

import com.google.gson.annotations.SerializedName


data class Offers(
    val page: Page
)

data class Page(
    val cards: List<Cards>
)

data class Cards(
    @SerializedName("card_type")
    val cardType: String,
    val card: Card
)

data class Card(
    val title: Title,
    val image: Image,
    val value: String,
    val attributes: Attributes,
    val description: Description
)

data class Image(
    val url: String,
    val size: Size

)

data class Size(
    val width: Int,
    val height: Int
)

data class Title(
    val value: String,
    val attributes: Attributes
)

data class Description(
    val value: String,
    val attributes: Attributes
)


data class Attributes(
    @SerializedName("text_color")
    val textColor: String,
    val font: Font
)

data class Font(
    val size: Int
)