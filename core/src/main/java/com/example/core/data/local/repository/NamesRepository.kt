package com.example.core.data.local.repository

import com.example.core.data.local.rao.Names

interface NamesRepository {
    fun setNames(firstName: String?, secondName: String?)

    fun getFirstName(): String?

    fun getSecondName(): String?
}

internal class NamesRepositoryImpl(
    private val names: Names
) : NamesRepository {

    override fun setNames(firstName: String?, secondName: String?) {
        names.firstName = firstName
        names.secondName = secondName
    }

    override fun getFirstName(): String? {
        return names.firstName
    }

    override fun getSecondName(): String? {
        return names.secondName
    }
}