package com.example.core.data.remote.extension

import com.example.core.model.BaseListModel
import com.example.core.service.response.BaseListResponse
import com.example.core.shared.NightResult
import com.example.core.shared.mapIfSuccess

fun <T, R> NightResult<BaseListResponse<T>>.mapDataIfSuccess(
    transform: (data: T) -> R
): NightResult<BaseListModel<R>> {
    return mapIfSuccess {
        val data: List<T> = it.results
        return if (data.isEmpty().not()) NightResult.Success(BaseListModel(data.map { eachData ->
            transform(
                eachData
            )
        }))
        else NightResult.Error.Local(message = "Server data is not available", cause = null)
    }
}
