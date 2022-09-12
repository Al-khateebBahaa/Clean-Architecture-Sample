package com.ism.task.presentation.feature_search

import com.ism.task.core.remote_api_config.NetworkResponse
import com.ism.task.data.remote.GenericResponse
import com.ism.task.data.remote.dto.SearchImagesDto
import com.ism.task.domain.repo.SearchRepo

class FakeSearchRepo : SearchRepo {


    /*
    * case: search query shouldn't be null or empty
    * case: data maybe empty
    * case: data is loaded successfully
    * case: data may failed
    * */

    override suspend fun searchForPhotos(
        searchQuery: String?,
        page: Int,
        pageCount: Int
    ): GenericResponse<SearchImagesDto>? {


        return if (searchQuery.isNullOrEmpty())
            return null
        else if (page == 0)
            return NetworkResponse.Success(SearchImagesDto())
        else
            NetworkResponse.Success(
                SearchImagesDto(
                    results = listOf(
                        SearchImagesDto.Result(),
                        SearchImagesDto.Result(),
                        SearchImagesDto.Result()
                    )
                )
            )

    }


}