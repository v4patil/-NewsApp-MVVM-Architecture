package com.vibhorpatil.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vibhorpatil.newsapp.databinding.ActivityMainBinding
import com.vibhorpatil.newsapp.ui.newscriteria.NewsCriteriaActivity
import com.vibhorpatil.newsapp.ui.search.SearchActivity
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadlineActivity
import com.vibhorpatil.newsapp.utils.AppConstant.BY_COUNTRY
import com.vibhorpatil.newsapp.utils.AppConstant.BY_LANGUAGE
import com.vibhorpatil.newsapp.utils.AppConstant.BY_SOURCE

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        addClickListener();
    }

    private fun addClickListener() {
        activityMainBinding.btnTopHeadline.setOnClickListener {
            startActivity(TopHeadlineActivity.getIntent(this))
        }

        activityMainBinding.btnNewsSource.setOnClickListener {
            startActivity(NewsCriteriaActivity.getIntent(this, BY_SOURCE))

        }

        activityMainBinding.btnCountries.setOnClickListener {
            startActivity(NewsCriteriaActivity.getIntent(this, BY_COUNTRY))
        }

        activityMainBinding.btnLanguages.setOnClickListener {
            startActivity(NewsCriteriaActivity.getIntent(this, BY_LANGUAGE))
        }

        activityMainBinding.btnSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java);
            startActivity(intent)
        }
    }
}