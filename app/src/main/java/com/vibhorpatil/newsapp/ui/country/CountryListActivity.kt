package com.vibhorpatil.newsapp.ui.country

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vibhorpatil.newsapp.NewsApplication
import com.vibhorpatil.newsapp.data.model.Country
import com.vibhorpatil.newsapp.databinding.ActivityCountryListBinding
import com.vibhorpatil.newsapp.di.component.DaggerActivityComponent
import com.vibhorpatil.newsapp.di.module.ActivityModule
import com.vibhorpatil.newsapp.ui.base.UiState
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadlineActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryListBinding

    @Inject
    lateinit var viewModel: CountryListViewModel

    @Inject
    lateinit var adapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityCountryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        setupClickListener()
        setUpObserver()
    }

    private fun setUpUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupClickListener() {
        adapter.setOnCountryClickListener { country ->
            val intent = Intent(this, TopHeadlineActivity::class.java)
            intent.putExtra("country_code", country.countryId)
            startActivity(intent)
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

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                        }

                        is UiState.Loading -> {
                            binding.recyclerView.visibility = View.GONE
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is UiState.Error -> {
                            binding.recyclerView.visibility = View.GONE
                            binding.progressBar.visibility = View.VISIBLE

                            Toast.makeText(this@CountryListActivity, it.errorMessage.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }
        }
    }

    private fun renderList(list: List<Country>) {
        adapter.addData(ArrayList(list))
    }

}