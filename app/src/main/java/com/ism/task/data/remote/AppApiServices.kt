package com.ism.task.data.remote

import com.ism.task.core.remote_api_config.BaseResponse
import com.ism.task.core.remote_api_config.NetworkResponse
import com.ism.task.data.remote.dto.SearchImagesDto
import com.ism.task.domain.model.SearchModel
import retrofit2.http.GET
import retrofit2.http.Query

typealias GenericResponse<S> = NetworkResponse<S, BaseResponse<String>>


private const val GET_SEARCH_TEXT = "search/photos"

interface AppApiServices {


    @GET(GET_SEARCH_TEXT)
    suspend fun getPhotos(
        @Query("query") searchTerms: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): GenericResponse<SearchImagesDto>

}