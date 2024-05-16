package com.example.core.data.local.repository

import com.example.core.data.local.rao.ContentLists
import com.example.core.model.Content


interface ContentListRepository {

    fun insertType(type: Content.Type): Content.Type

    fun insertFoundedList(list: List<Content>): List<Content>

    fun insertFirstList(list: List<Content>): List<Content>

    fun insertSecondList(list: List<Content>): List<Content>

    fun getType(): Content.Type

    fun getFoundedList(): List<Content>?

    fun getCountCompletedList(): Int

    fun getMatchingList(): List<Content>?
}

internal class ContentListRepositoryImpl(
    private val contentLists: ContentLists
) : ContentListRepository {

    override fun insertType(type: Content.Type): Content.Type {
        contentLists.resetLists()
        contentLists.type = type
        return contentLists.type
    }

    override fun insertFoundedList(list: List<Content>): List<Content> {
        contentLists.resetLists()
        contentLists.foundedList = list
        return list
    }

    override fun insertFirstList(list: List<Content>): List<Content> {
        contentLists.firstList = list
        return list
    }

    override fun insertSecondList(list: List<Content>): List<Content> {
        contentLists.secondList = list
        return list
    }

    override fun getType(): Content.Type {
        return contentLists.type
    }

    override fun getFoundedList(): List<Content>? {
        return contentLists.foundedList
    }

    override fun getCountCompletedList(): Int {
        return listOf(contentLists.firstList, contentLists.secondList).count { it != null }
    }

    override fun getMatchingList(): List<Content>? {
        val firstList = contentLists.firstList ?: emptyList()
        val secondList = contentLists.secondList ?: emptyList()

        return if (firstList.isEmpty() || secondList.isEmpty()) {
            firstList.ifEmpty { secondList }
        } else {
            firstList.intersect(secondList.toSet()).toList().ifEmpty { null }
        }
    }
}