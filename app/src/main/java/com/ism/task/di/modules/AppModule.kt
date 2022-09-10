package com.ism.task.di.modules

import android.content.Context
import com.ism.task.data.remote.AppApiServices
import com.ism.task.data.repo.SearchRepoImpl
import com.ism.task.di.AppApplication
import com.ism.task.domain.repo.SearchRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): AppApplication {
        return app as AppApplication
    }

    @Singleton
    @Provides
    fun provideSearchRepo(api: AppApiServices): SearchRepo {
        return SearchRepoImpl(api)
    }


}
