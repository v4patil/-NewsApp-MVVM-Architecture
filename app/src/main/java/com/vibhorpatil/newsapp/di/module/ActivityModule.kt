package com.vibhorpatil.newsapp.di.module

import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.domain.model.NewsCriteria
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    @Named("articleList")
    fun provideEmptyList(): ArrayList<Article> {
        return ArrayList()
    }

    @Provides
    @Named("newsCriteriaList")
    fun provideNewsCriteriaEmptyList(): ArrayList<NewsCriteria> {
        return ArrayList()
    }

}