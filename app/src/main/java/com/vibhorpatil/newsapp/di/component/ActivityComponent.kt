package com.vibhorpatil.newsapp.di.component

import com.vibhorpatil.newsapp.di.module.ActivityModule
import com.vibhorpatil.newsapp.di.module.ActivityScope
import com.vibhorpatil.newsapp.ui.country.CountryListActivity
import com.vibhorpatil.newsapp.ui.language.LanguageListActivity
import com.vibhorpatil.newsapp.ui.newssource.NewsSourceActivity
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)

    fun inject(activity: NewsSourceActivity)

    fun inject(activity: CountryListActivity)

    fun inject(activity: LanguageListActivity)
}