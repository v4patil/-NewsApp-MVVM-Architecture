package com.vibhorpatil.newsapp.ui.topheadline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadlineActivity.Companion.EXTRA_LANGUAGE_ID
import com.vibhorpatil.newsapp.utils.AppConstant.COUNTRY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHeadlineActivity : AppCompatActivity() {

    val topHeadLineViewmodel: TopHeadLineViewmodel by viewModels()

    companion object {
        private const val EXTRA_LANGUAGE_ID = "language_id"
        private const val EXTRA_SOURCE_ID = "source_id"
        private const val EXTRA_COUNTRY_CODE = "country_code"

        fun getIntent(
            context: Context,
            countryId: String? = null,
            languageId: String? = null,
            sourceId: String? = null
        ): Intent = Intent(context, TopHeadlineActivity::class.java).apply {
            countryId?.let { putExtra(EXTRA_COUNTRY_CODE, it) }
            languageId?.let { putExtra(EXTRA_LANGUAGE_ID, it) }
            sourceId?.let { putExtra(EXTRA_SOURCE_ID, it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopHeadLineRoute({ finish() })
        }
        getIntentData()
    }

    private fun getIntentData() {
        val languageId = intent.getStringExtra(EXTRA_LANGUAGE_ID)
        val sourceId = intent.getStringExtra(EXTRA_SOURCE_ID)
        val countryCode = intent.getStringExtra(EXTRA_COUNTRY_CODE)

        when {
            languageId != null -> {
                topHeadLineViewmodel.getTopHeadLinesByLanguage(languageId)
            }

            sourceId != null -> {
                topHeadLineViewmodel.getTopHeadLinesBySource(sourceId)
            }

            countryCode != null -> {
                topHeadLineViewmodel.getTopHeadLines(countryCode)
            }

            else -> {
                topHeadLineViewmodel.getTopHeadLines(COUNTRY)
            }
        }
    }
}