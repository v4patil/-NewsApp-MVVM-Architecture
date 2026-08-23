package com.vibhorpatil.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.domain.model.NewsCriteria
import com.vibhorpatil.newsapp.data.model.NewsSource
import com.vibhorpatil.newsapp.domain.repository.NewsRepository
import com.vibhorpatil.newsapp.ui.base.ViewModelProviderFactory
import com.vibhorpatil.newsapp.ui.newscriteria.NewsCriteriaViewModel
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
    fun provideTopHeadLineViewModel(newsRepository: NewsRepository) : TopHeadLineViewmodel{
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(TopHeadLineViewmodel::class){
                TopHeadLineViewmodel(newsRepository)
            }
        )[TopHeadLineViewmodel::class.java]

    }

    @Provides
    fun provideNewsCriteriaViewModel(@ApplicationContext context: Context, newsRepository: NewsRepository) : NewsCriteriaViewModel {
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(NewsCriteriaViewModel::class){
                NewsCriteriaViewModel(context,newsRepository)
            }
        )[NewsCriteriaViewModel::class.java]
    }

//    @Provides
//    fun provideSearchViewModel(repository: TopHeadLineRepository) : SearchViewModel {
//        return ViewModelProvider(
//            activity,
//            ViewModelProviderFactory(SearchViewModel::class) {
//                SearchViewModel(repository)
//            }
//        )[SearchViewModel::class.java]
//    }


    @Provides
    fun provideEmptyList() = ArrayList<Article>()

    @Provides
    fun provideNewsSourceEmptyList() =  ArrayList<NewsSource>()

    @Provides
    fun provideNewsCriteriaEmptyList() = ArrayList<NewsCriteria>()

    @Provides
    fun provideTopHeadLineAdapter() = TopHeadLineAdapter(ArrayList())

}