package com.ism.task.presentation.search

import com.ism.task.domain.model.SearchModel

sealed class PhotoState {

    data class SearchSuccess(val data: SearchModel) : PhotoState()

    data class SearchFail(val error: String) : PhotoState()

    object EmptyResult : PhotoState()


    data class LoadingStatus(val status: Boolean) : PhotoState()
}
