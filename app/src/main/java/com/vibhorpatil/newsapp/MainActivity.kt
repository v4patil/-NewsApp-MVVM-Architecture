package com.vibhorpatil.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.vibhorpatil.newsapp.ui.HomeScreenRoute
import com.vibhorpatil.newsapp.ui.newscriteria.NewsCriteriaActivity
import com.vibhorpatil.newsapp.ui.search.SearchActivity
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadlineActivity
import com.vibhorpatil.newsapp.utils.AppConstant.BY_COUNTRY
import com.vibhorpatil.newsapp.utils.AppConstant.BY_LANGUAGE
import com.vibhorpatil.newsapp.utils.AppConstant.BY_SOURCE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            HomeScreenRoute({navigateTo(it)})
        }
    }

    private fun navigateTo(position: Int) {
        when (position) {
            0 -> {
                startActivity(TopHeadlineActivity.getIntent(this))
            }

            1 -> {
                startActivity(NewsCriteriaActivity.getIntent(this, BY_SOURCE))
            }

            2 -> {
                startActivity(NewsCriteriaActivity.getIntent(this, BY_COUNTRY))
            }

            3 -> {
                startActivity(NewsCriteriaActivity.getIntent(this, BY_LANGUAGE))
            }

            4 -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }
        }
    }

}