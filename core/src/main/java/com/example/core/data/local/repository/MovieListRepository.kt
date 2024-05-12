package com.example.core.data.local.repository

import com.example.core.data.local.rao.MovieLists
import com.example.core.model.Movie

interface MovieListRepository {

    fun insertFoundedList(list: List<Movie>): List<Movie>

    fun insertFirstList(list: List<Movie>): List<Movie>

    fun insertSecondList(list: List<Movie>): List<Movie>

    fun getFoundedList(): List<Movie>?

    fun getCountCompletedList(): Int

    fun getMatchingList(): List<Movie>?
}

internal class MovieListRepositoryImpl(
    private val movieLists: MovieLists
) : MovieListRepository {

    override fun insertFoundedList(list: List<Movie>): List<Movie> {
        movieLists.foundedList = list
        return list
    }

    override fun insertFirstList(list: List<Movie>): List<Movie> {
        movieLists.firstList = list
        return list
    }

    override fun insertSecondList(list: List<Movie>): List<Movie> {
        movieLists.secondList = list
        return list
    }

    override fun getFoundedList(): List<Movie>? {
        return movieLists.foundedList
    }

    override fun getCountCompletedList(): Int {
        return listOf(movieLists.firstList, movieLists.secondList).count { it != null }
    }

    override fun getMatchingList(): List<Movie>? {
        val firstList = movieLists.firstList ?: emptyList()
        val secondList = movieLists.secondList ?: emptyList()

        return if (firstList.isEmpty() || secondList.isEmpty()) {
            firstList.ifEmpty { secondList }
        } else {
            firstList.intersect(secondList.toSet()).toList().ifEmpty { null }
        }
    }
}