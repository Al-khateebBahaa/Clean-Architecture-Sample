package com.ism.task.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ism.task.core.remote_api_config.NetworkResponse
import com.ism.task.domain.use_case.image_search.PhotoSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoSearchViewModel @Inject constructor(
    private val searchUseCase: PhotoSearchUseCase
) : ViewModel() {


    private var mSearchQuery = "Random"

    init {
        getSearchImage(mSearchQuery, 1)
    }


    private val _searchState by lazy { MutableStateFlow<PhotoState>(PhotoState.EmptyResult) }
    val searchState by lazy { _searchState.asStateFlow() }


    fun getSearchImage(
        searchQuery: String = mSearchQuery,
        page: Int
    ) = viewModelScope.launch {

        mSearchQuery = searchQuery

        val invokeState = searchUseCase.invoke(searchQuery, page)

        _searchState.emit(PhotoState.LoadingStatus(true))

        if (invokeState is NetworkResponse.Success) {
            if (invokeState.body.results.isNullOrEmpty())
                _searchState.emit(PhotoState.EmptyResult)
            else
                _searchState.emit(PhotoState.SearchSuccess(invokeState.body))
        } else {
            _searchState.emit(PhotoState.SearchFail("Failed to search, Unexpected Error..!"))
        }

        _searchState.emit(PhotoState.LoadingStatus(false))


    }

}