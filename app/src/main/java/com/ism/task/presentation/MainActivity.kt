package com.ism.task.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.ism.task.R
import com.ism.task.databinding.ActivityMainBinding
import com.ism.task.domain.model.HorizontalMarginItemDecoration
import com.ism.task.domain.model.OnPagingMovedListener
import com.ism.task.presentation.search.PhotoSearchViewModel
import com.ism.task.presentation.search.PhotoState
import com.ism.task.presentation.search.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.abs

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnPagingMovedListener {

    private val viewModel: PhotoSearchViewModel by viewModels()
    private val mBinding: ActivityMainBinding by viewBinding()

    private val mPhotosAdapter: SearchAdapter by lazy { SearchAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFlows()

        initPager()

        initClick()

    }


    private fun initPager() {


        mBinding.pagerVw.offscreenPageLimit = 1

// Add a PageTransformer that translates the next and previous items horizontally
// towards the center of the screen, which makes them visible
        val nextItemVisiblePx = 64
        val currentItemHorizontalMarginPx = 64
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        mBinding.pagerVw.setPageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.scaleY = 1 - (0.25f * abs(position))
        }

// The ItemDecoration gives the current (centered) item horizontal margin so that
// it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = HorizontalMarginItemDecoration(
        )
        mBinding.pagerVw.addItemDecoration(itemDecoration)


        mBinding.pagerVw.adapter = mPhotosAdapter

    }


    //Init listeners here, we can also assign it with data binding
    private fun initClick() {

        mBinding.searchBtn.setOnClickListener {

            mPhotosAdapter.page = 1
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
                    mPhotosAdapter.setPhotosData(it.data.results?.filterNotNull() , it.data.total)
                }


            }
        }

    }

    private fun showOrHideProgress(viewStatus: Boolean) {

        mBinding.loadingProg.isVisible = viewStatus

    }

    //Paging listener without use any external library
    override fun onItemMoved(position: Int, data: Any?) {
        viewModel.getSearchImage(page = data as Int)

    }


}