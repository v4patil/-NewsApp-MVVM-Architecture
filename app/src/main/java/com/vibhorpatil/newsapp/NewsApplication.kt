package com.vibhorpatil.newsapp

import android.app.Application
import com.vibhorpatil.newsapp.di.component.ApplicationComponent
import com.vibhorpatil.newsapp.di.component.DaggerApplicationComponent
import com.vibhorpatil.newsapp.di.module.ApplicationModule

class NewsApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}