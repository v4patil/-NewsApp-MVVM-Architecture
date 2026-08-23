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

//cd app/src/main/java/com/vibhorpatil/newsapp
//tree /F /A

/*
com.vibhorpatil.newsapp
│
├── data
│   ├── api
│   │   └── NetworkService.kt
│   ├── interceptor
│   │   ├── CacheInterceptor.kt
│   │   └── ForceCacheInterceptor.kt
│   ├── model
│   │   ├── ArticleDto.kt
│   │   ├── NewsSourceDto.kt
│   │   └── ...
│   └── repository
│       └── NewsRepositoryImpl.kt
│
├── domain
│   ├── model
│   │   ├── Article.kt
│   │   ├── NewsCriteria.kt
│   │   └── NewsSource.kt
│   └── repository
│       └── NewsRepository.kt
│
├── ui
│   ├── base
│   ├── search
│   ├── newscriteria
│   └── topheadline
│
├── di
│   ├── component
│   └── module
│
└── utils
 */