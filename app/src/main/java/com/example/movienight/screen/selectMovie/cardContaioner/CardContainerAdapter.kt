package com.example.movienight.screen.selectMovie.cardContaioner

import android.content.Context
import android.view.View
import com.asynctaskcoffee.cardstack.CardContainerAdapter
import com.example.core.model.Content

class CardContainerAdapter(
    private val context: Context,
    private val onContentClickListener: (Content) -> Unit
) : CardContainerAdapter() {

    private val list = mutableListOf<Content>()

    override fun getItem(position: Int) = list[position]

    override fun getView(position: Int): View {
        return ContentCardView(
            context,
            content = getItem(position),
            onContentClickListener = onContentClickListener
        )
    }

    override fun getCount(): Int = list.size

    fun submitList(list: List<Content>) {
        this.list.clear()
        this.list.addAll(list)
    }
}