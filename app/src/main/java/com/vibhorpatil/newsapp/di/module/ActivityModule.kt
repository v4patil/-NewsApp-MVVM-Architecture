package com.vibhorpatil.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.data.repository.TopHeadLineRepository
import com.vibhorpatil.newsapp.ui.base.ViewModelProviderFactory
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadLineAdapter
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadLineViewmodel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity ) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideTopHeadLineViewModel(topHeadLineRepository: TopHeadLineRepository) : TopHeadLineViewmodel{
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(TopHeadLineViewmodel::class){
                TopHeadLineViewmodel(topHeadLineRepository)
            }
        )[TopHeadLineViewmodel::class.java]

    }

    @Provides
    fun provideEmptyList() = ArrayList<Article>()

    @Provides
    fun provideTopHeadLineAdapter() = TopHeadLineAdapter(ArrayList())


}