package com.example.movienight.screen.selectMovie.cardContaioner

import android.content.Context
import android.view.View
import com.asynctaskcoffee.cardstack.CardContainerAdapter
import com.example.core.model.Movie

class CardContainerAdapter(
    private val context: Context,
    private val onMovieClickListener: (Movie) -> Unit
) : CardContainerAdapter() {

    private val list = mutableListOf<Movie>()

    override fun getItem(position: Int) = list[position]

    override fun getView(position: Int): View {
        return MovieCardView(
            context,
            movie = getItem(position),
            onMovieClickListener = onMovieClickListener
        )
    }

    override fun getCount(): Int = list.size

    fun submitList(list: List<Movie>) {
        this.list.clear()
        this.list.addAll(list)
    }
}