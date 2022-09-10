package com.ism.task.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.ism.task.R
import com.ism.task.databinding.ActivityMainBinding
import com.ism.task.domain.model.OnPagingMovedListener
import com.ism.task.domain.model.ZoomOutPageTransformer
import com.ism.task.presentation.search.PhotoSearchViewModel
import com.ism.task.presentation.search.PhotoState
import com.ism.task.presentation.search.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnPagingMovedListener {

    private val viewModel: PhotoSearchViewModel by viewModels()
    private val mBinding: ActivityMainBinding by viewBinding()

    private val mPhotosAdapter: SearchAdapter by lazy { SearchAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFlows()

        mBinding.pagerVw.adapter = mPhotosAdapter
        mBinding.pagerVw.setPageTransformer(ZoomOutPageTransformer())


        initClick()

    }


    //Init listeners here, we can also assign it with data binding

    private fun initClick() {

        mBinding.searchBtn.setOnClickListener {

            viewModel.getSearchImage(mBinding.searchEd.text.toString(), 1)

        }

        mBinding.searchEd.doOnTextChanged { text, start, before, count ->

            mBinding.searchBtn.isVisible = !text.isNullOrEmpty()

            mBinding.backBtn.isVisible = text.isNullOrEmpty()

        }



        mBinding.backBtn.setOnClickListener {

            mBinding.searchEd.setText("")

        }

    }



    //Collect flows
    private fun initFlows() {

        lifecycleScope.launchWhenStarted {

            viewModel.searchState.collectLatest {

                if (it is PhotoState.LoadingStatus) {
                    showOrHideProgress(it.status)
                } else if (it is PhotoState.SearchSuccess) {
                    mPhotosAdapter.setPhotosData(it.data.results?.filterNotNull())
                }


            }
        }

    }


    private fun showOrHideProgress(viewStatus: Boolean) {

        mBinding.loadingProg.isVisible = viewStatus


    }

    override fun onItemMoved(position: Int, data: Any?) {
        viewModel.getSearchImage(page = data as Int)

    }


}