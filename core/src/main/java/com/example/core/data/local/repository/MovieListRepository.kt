package com.example.core.data.local.repository

import com.example.core.data.local.rao.MovieLists
import com.example.core.model.Movie

interface MovieListRepository {

    fun insertFirstList(list: List<Movie>): List<Movie>?

    fun insertSecondList(list: List<Movie>): List<Movie>?

    fun getCountCompletedList(): Int

    fun getMatchingList(): List<Movie>?
}

internal class MovieListRepositoryImpl(
    private val movieLists: MovieLists
) : MovieListRepository {

    override fun insertFirstList(list: List<Movie>): List<Movie>? {
        movieLists.firstList = list
        return movieLists.firstList
    }

    override fun insertSecondList(list: List<Movie>): List<Movie>? {
        movieLists.secondList = list
        return movieLists.secondList
    }

    override fun getCountCompletedList(): Int {
        return listOf(movieLists.firstList, movieLists.secondList).count { it != null }
    }

    override fun getMatchingList(): List<Movie>? {
        val firstList = movieLists.firstList ?: listOf()
        val secondList = movieLists.secondList ?: listOf()

        return firstList.intersect(secondList.toSet()).toList().ifEmpty { null }
    }
}