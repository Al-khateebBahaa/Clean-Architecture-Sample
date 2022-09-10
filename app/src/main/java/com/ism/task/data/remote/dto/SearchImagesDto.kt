package com.ism.task.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ism.task.domain.model.SearchModel

data class SearchImagesDto(
    @SerializedName("results")
    val results: List<Result?>? = null,
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null
) {
    data class Result(
        @SerializedName("blur_hash")
        val blurHash: String? = null,
        @SerializedName("color")
        val color: String? = null,
        @SerializedName("created_at")
        val createdAt: String? = null,
        @SerializedName("current_user_collections")
        val currentUserCollections: List<Any?>? = null,
        @SerializedName("description")
        val description: String? = null,
        @SerializedName("height")
        val height: Int? = null,
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("liked_by_user")
        val likedByUser: Boolean? = null,
        @SerializedName("likes")
        val likes: Int? = null,
        @SerializedName("links")
        val links: Links? = null,
        @SerializedName("urls")
        val urls: Urls? = null,
        @SerializedName("user")
        val user: User? = null,
        @SerializedName("width")
        val width: Int? = null
    ) {
        data class Links(
            @SerializedName("download")
            val download: String? = null,
            @SerializedName("html")
            val html: String? = null,
            @SerializedName("self")
            val self: String? = null
        )

        data class Urls(
            @SerializedName("full")
            val full: String? = null,
            @SerializedName("raw")
            val raw: String? = null,
            @SerializedName("regular")
            val regular: String? = null,
            @SerializedName("small")
            val small: String? = null,
            @SerializedName("thumb")
            val thumb: String? = null
        )

        data class User(
            @SerializedName("first_name")
            val firstName: String? = null,
            @SerializedName("id")
            val id: String? = null,
            @SerializedName("instagram_username")
            val instagramUsername: String? = null,
            @SerializedName("last_name")
            val lastName: String? = null,
            @SerializedName("links")
            val links: Links? = null,
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("portfolio_url")
            val portfolioUrl: String? = null,
            @SerializedName("profile_image")
            val profileImage: ProfileImage? = null,
            @SerializedName("twitter_username")
            val twitterUsername: String? = null,
            @SerializedName("username")
            val username: String? = null
        ) {
            data class Links(
                @SerializedName("html")
                val html: String? = null,
                @SerializedName("likes")
                val likes: String? = null,
                @SerializedName("photos")
                val photos: String? = null,
                @SerializedName("self")
                val self: String? = null
            )

            data class ProfileImage(
                @SerializedName("large")
                val large: String? = null,
                @SerializedName("medium")
                val medium: String? = null,
                @SerializedName("small")
                val small: String? = null
            )
        }
    }
}

fun SearchImagesDto.toSearchModel(): SearchModel =
    SearchModel(
        results = results,
        totalPages = totalPages,
        total = total

    )

