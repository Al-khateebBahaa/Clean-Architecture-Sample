package com.ism.task.domain.use_case.image_search

import com.ism.task.core.remote_api_config.NetworkResponse
import com.ism.task.data.remote.GenericResponse
import com.ism.task.data.remote.dto.toSearchModel
import com.ism.task.domain.model.SearchModel
import com.ism.task.domain.repo.SearchRepo
import java.io.IOException
import javax.inject.Inject

class PhotoSearchUseCase @Inject constructor(
    private val repo: SearchRepo
) {

    suspend operator fun invoke(
        searchQuery: String,
        page: Int
    ): GenericResponse<SearchModel> {

        return try {
            val mPhotos = repo.searchForPhotos(searchQuery, page, 20)

            if (mPhotos is NetworkResponse.Success) {
                NetworkResponse.Success(mPhotos.body.toSearchModel())
            } else {
                NetworkResponse.UnknownError(Throwable("Unknown error occurs"))
            }
        } catch (e: IOException) {
            NetworkResponse.NetworkError(e)

        }


    }

}