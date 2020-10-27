package com.prokarma.tmobile

import com.prokarma.tmobile.ui.offers.getImageNewHeight
import org.junit.Assert
import org.junit.Test

//With more time provided i would write more test cases to make
//sure value updated in liveData is reflected to UI
//-When app loads to fails from service make sure it loads from Shared Preference if it
//is available etc.

class ImageNewHeightTest {

    @Test
    fun testImageNewHeight() {
        Assert.assertEquals(468, getImageNewHeight(350, 300, 546))
        Assert.assertEquals(400, getImageNewHeight(540, 540, 400))
        Assert.assertEquals(622, getImageNewHeight(540, 840, 400))
    }
}