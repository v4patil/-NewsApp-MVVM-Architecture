package com.vibhorpatil.newsapp.di.component

import android.content.Context
import com.vibhorpatil.newsapp.NewsApplication
import com.vibhorpatil.newsapp.data.api.NetworkService
import com.vibhorpatil.newsapp.data.repository.NewsRepositoryImpl
import com.vibhorpatil.newsapp.di.module.ApplicationContext
import com.vibhorpatil.newsapp.di.module.ApplicationModule
import com.vibhorpatil.newsapp.domain.repository.NewsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getNewsRepository(): NewsRepository
}