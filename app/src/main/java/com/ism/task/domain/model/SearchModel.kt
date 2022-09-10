package com.ism.task.domain.model

import com.google.gson.annotations.SerializedName
import com.ism.task.data.remote.dto.SearchImagesDto

data class SearchModel(
    @SerializedName("results")
    val results: List<SearchImagesDto.Result?>? = null,
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null
)