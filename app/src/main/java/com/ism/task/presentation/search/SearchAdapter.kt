package com.ism.task.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ism.task.data.remote.dto.SearchImagesDto
import com.ism.task.databinding.SearchItemRecyBinding
import com.ism.task.domain.model.OnPagingMovedListener

class SearchAdapter(private val pagingListener: OnPagingMovedListener) :
    ListAdapter<SearchImagesDto.Result, SearchAdapter.PhotosViewHolder>(PhotosDiffCallback) {


    private val mPhotosData: ArrayList<SearchImagesDto.Result> by lazy { arrayListOf() }
    var page = 1
    var isLoading = false
    var isLastPage = false


    fun setPhotosData(mPhotosData: List<SearchImagesDto.Result>?, totalItems: Int?) {

        if (!mPhotosData.isNullOrEmpty()) {

            if (page == 1)
                this.mPhotosData.clear()

            this.mPhotosData.addAll(mPhotosData)
            notifyItemRangeInserted(this.mPhotosData.size - 1, mPhotosData.size)


            if (totalItems == mPhotosData.size)
                isLastPage = true
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder =

        PhotosViewHolder(
            SearchItemRecyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {

        if (mPhotosData.size - 2 == position && !isLoading && !isLastPage) {
            pagingListener.onItemMoved(position, ++page)
        }

        holder.bind(mPhotosData[position])


    }

    override fun getItemCount() = mPhotosData.size


    class PhotosViewHolder(val view: SearchItemRecyBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(data: SearchImagesDto.Result) {

            view.data = data

        }

    }


    private object PhotosDiffCallback : DiffUtil.ItemCallback<SearchImagesDto.Result>() {
        override fun areItemsTheSame(
            oldItem: SearchImagesDto.Result,
            newItem: SearchImagesDto.Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchImagesDto.Result,
            newItem: SearchImagesDto.Result
        ): Boolean {
            return oldItem == newItem
        }
    }


}