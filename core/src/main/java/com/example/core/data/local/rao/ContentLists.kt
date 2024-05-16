package com.example.core.data.local.rao

import com.example.core.model.Content

internal class ContentLists {
    var type = Content.Type.MOVIE

    var foundedList: List<Content>? = null

    var firstList: List<Content>? = null
    var secondList: List<Content>? = null

    fun resetLists(){
        foundedList = null
        firstList = null
        secondList = null
    }
}