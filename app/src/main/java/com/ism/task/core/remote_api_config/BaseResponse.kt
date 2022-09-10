package com.ism.task.core.remote_api_config

import com.google.gson.annotations.SerializedName

class BaseResponse<T> {
    @SerializedName("message")
    val message: String? = null
    @SerializedName("data")
    val data: T? = null
    @SerializedName("success")
    var success: Boolean? = false


}

