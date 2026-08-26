package com.vibhorpatil.newsapp.ui.newscriteria

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vibhorpatil.newsapp.domain.model.NewsCriteria
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadlineActivity
import com.vibhorpatil.newsapp.utils.AppConstant.BY_COUNTRY
import com.vibhorpatil.newsapp.utils.AppConstant.BY_LANGUAGE
import com.vibhorpatil.newsapp.utils.AppConstant.BY_SOURCE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsCriteriaActivity : AppCompatActivity() {

    private val viewModel: NewsCriteriaViewModel by viewModels()

    companion object {
        const val FILTER_BY = "filterBy"

        fun getIntent(context: Context, filterBy: Int): Intent {
            return Intent(context, NewsCriteriaActivity::class.java).apply {
                putExtra(FILTER_BY, filterBy)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsCriteriaRoute({ finish() }, { newsCriteria ->
                navigateTo(newsCriteria)
            })
        }
        getIntentData()
    }

    private fun getIntentData() {
        val bundle = intent.extras ?: return
        bundle.let {
            val filterBy = bundle.getInt(FILTER_BY)
            viewModel.getData(filterBy)
        }
    }

    private fun navigateTo(newsCriteria: NewsCriteria) {
        when (viewModel.filterBy) {
            BY_COUNTRY -> {
                startActivity(TopHeadlineActivity.getIntent(this, countryId = newsCriteria.id))
            }

            BY_LANGUAGE -> {
                startActivity(TopHeadlineActivity.getIntent(this, languageId = newsCriteria.id))
            }

            BY_SOURCE -> {
                startActivity(TopHeadlineActivity.getIntent(this, sourceId = newsCriteria.id))
            }
        }
    }

}