package com.ism.task.domain.repo

import com.ism.task.data.remote.GenericResponse
import com.ism.task.data.remote.dto.SearchImagesDto

interface SearchRepo {

    suspend fun searchForPhotos(
        searchQuery: String,
        page: Int,
        pageCount: Int
    ): GenericResponse<SearchImagesDto>

}