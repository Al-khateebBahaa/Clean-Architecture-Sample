package com.ism.task.data.repo

import com.ism.task.data.remote.AppApiServices
import com.ism.task.data.remote.GenericResponse
import com.ism.task.data.remote.dto.SearchImagesDto
import com.ism.task.domain.repo.SearchRepo
import javax.inject.Inject

class SearchRepoImpl @Inject constructor(
    private val api: AppApiServices
) : SearchRepo {

    override suspend fun searchForPhotos(
        searchQuery: String?,
        page: Int,
        pageCount: Int
    ): GenericResponse<SearchImagesDto> {
        return api.getPhotos(searchQuery!!, page, pageCount)
    }
}