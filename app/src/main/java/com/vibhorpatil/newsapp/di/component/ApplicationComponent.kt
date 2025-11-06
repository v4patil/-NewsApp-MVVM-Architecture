package com.vibhorpatil.newsapp.di.component

import android.content.Context
import com.vibhorpatil.newsapp.NewsApplication
import com.vibhorpatil.newsapp.di.module.ApplicationContext
import com.vibhorpatil.newsapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context
}