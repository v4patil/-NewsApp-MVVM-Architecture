package com.vibhorpatil.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.data.model.Country
import com.vibhorpatil.newsapp.data.model.Language
import com.vibhorpatil.newsapp.data.model.NewsSource
import com.vibhorpatil.newsapp.data.repository.NewsSourceRepository
import com.vibhorpatil.newsapp.data.repository.TopHeadLineRepository
import com.vibhorpatil.newsapp.ui.base.ViewModelProviderFactory
import com.vibhorpatil.newsapp.ui.country.CountryListViewModel
import com.vibhorpatil.newsapp.ui.language.LanguageListViewModel
import com.vibhorpatil.newsapp.ui.newssource.NewsSourceViewModel
import com.vibhorpatil.newsapp.ui.search.SearchViewModel
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
    fun provideNewsSourceViewModel(newsSourceRepository: NewsSourceRepository): NewsSourceViewModel {
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(NewsSourceViewModel::class) {
                NewsSourceViewModel(newsSourceRepository)
            }
        )[NewsSourceViewModel::class.java]
    }

    @Provides
    fun provideSearchViewModel(repository: TopHeadLineRepository) : SearchViewModel {
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(SearchViewModel::class) {
                SearchViewModel(repository)
            }
        )[SearchViewModel::class.java]
    }

    @Provides
    fun providesCountryListViewModel() : CountryListViewModel {
        return CountryListViewModel()
    }

    @Provides
    fun provideEmptyList() = ArrayList<Article>()

    @Provides
    fun provideNewsSourceEmptyList() =  ArrayList<NewsSource>()

    @Provides
    fun provideCountryNameEmptyList() =  ArrayList<Country>()

    @Provides
    fun provideLanguageNameEmptyList() = ArrayList<Language>()

    @Provides
    fun provideTopHeadLineAdapter() = TopHeadLineAdapter(ArrayList())

    @Provides
    fun provideLanguageListViewModel() = LanguageListViewModel()


}