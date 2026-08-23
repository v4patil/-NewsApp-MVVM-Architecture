package com.vibhorpatil.newsapp.ui.newscriteria

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vibhorpatil.newsapp.NewsApplication
import com.vibhorpatil.newsapp.databinding.ActivityNewsCriteriaBinding
import com.vibhorpatil.newsapp.di.component.DaggerActivityComponent
import com.vibhorpatil.newsapp.di.module.ActivityModule
import com.vibhorpatil.newsapp.ui.base.UiState
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadlineActivity
import com.vibhorpatil.newsapp.utils.AppConstant.BY_COUNTRY
import com.vibhorpatil.newsapp.utils.AppConstant.BY_LANGUAGE
import com.vibhorpatil.newsapp.utils.AppConstant.BY_SOURCE
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsCriteriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsCriteriaBinding

    @Inject
    lateinit var viewModel: NewsCriteriaViewModel

    @Inject
    lateinit var adapter: NewsCriteriaAdapter

    companion object {
        const val FILTER_BY = "filterBy"

        fun getIntent(context: Context, filterBy: Int): Intent {
            return Intent(context, NewsCriteriaActivity::class.java).apply {
                putExtra(FILTER_BY, filterBy)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityNewsCriteriaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        getIntentData()
        setupObserver()
        setupClickListener()
    }

    private fun getIntentData() {
        val bundle = intent.extras ?: return
        bundle.let {
            val filterBy = bundle.getInt(FILTER_BY)
            viewModel.getData(filterBy)
        }
    }

    private fun setupUI() {
        val recyclerView = binding.rvList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }


    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.tvEmpty.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvEmpty.visibility = View.VISIBLE
                        }

                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvEmpty.visibility = View.GONE
                            binding.rvList.visibility = View.VISIBLE

                            if (it.data.isEmpty()) {
                                binding.tvEmpty.visibility = View.VISIBLE
                                binding.rvList.visibility = View.GONE
                            } else {
                                adapter.setData(it.data)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupClickListener() {
        adapter.setOnItemClickListener { newsCriteria ->
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


    private fun injectDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

    }
}