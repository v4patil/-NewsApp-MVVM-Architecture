package com.vibhorpatil.newsapp.ui.topheadline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vibhorpatil.newsapp.NewsApplication
import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.databinding.ActivityTopHeadlineBinding
import com.vibhorpatil.newsapp.di.component.DaggerActivityComponent
import com.vibhorpatil.newsapp.di.module.ActivityModule
import com.vibhorpatil.newsapp.ui.base.UiState
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadlineActivity.Companion.EXTRA_COUNTRY_CODE
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadlineActivity.Companion.EXTRA_LANGUAGE_ID
import com.vibhorpatil.newsapp.utils.AppConstant.COUNTRY
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

class TopHeadlineActivity : AppCompatActivity() {

    @Inject
    lateinit var topHeadLineViewmodel: TopHeadLineViewmodel

    @Inject
    lateinit var adapter: TopHeadLineAdapter

    private lateinit var binding: ActivityTopHeadlineBinding

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
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityTopHeadlineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        setupUI()
        setupObserver()
    }

    private fun injectDependencies(){
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
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

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                topHeadLineViewmodel.uiState.collect{
                    when(it){
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE

                            renderList(ArrayList(it.data))
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@TopHeadlineActivity, it.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(articleList: ArrayList<Article>){
        adapter.addData(articleList)
    }
}