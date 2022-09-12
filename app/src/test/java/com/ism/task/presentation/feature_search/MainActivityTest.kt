package com.ism.task.presentation.feature_search

import com.google.common.truth.Truth
import com.ism.task.core.remote_api_config.NetworkResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class MainActivityTest {

    private lateinit var mFakeRepo: FakeSearchRepo


    @Before
    fun setup() {
        mFakeRepo = FakeSearchRepo()
    }


    @Test
    fun `check search query with empty search query then return nothing`() = runTest {

        val result = mFakeRepo.searchForPhotos("", 1, 20)

        Truth.assertThat(result).isNull()

    }

    @Test
    fun `check search query with valid search query then return empty list`() = runTest {

        val result = mFakeRepo.searchForPhotos("test", 0, 20) as NetworkResponse.Success

        Truth.assertThat(result.body.results).isNull()
    }

    @Test
    fun `check search query with valid search params then return list`() = runTest {

        val result = mFakeRepo.searchForPhotos("test", 1, 20) as NetworkResponse.Success

        Truth.assertThat(result.body.results).isNotEmpty()

    }


}