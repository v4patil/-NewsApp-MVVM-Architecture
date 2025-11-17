package com.vibhorpatil.newsapp.ui.language

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.vibhorpatil.newsapp.NewsApplication
import com.vibhorpatil.newsapp.R
import com.vibhorpatil.newsapp.data.model.Language
import com.vibhorpatil.newsapp.databinding.ActivityLanguageListBinding
import com.vibhorpatil.newsapp.di.component.DaggerActivityComponent
import com.vibhorpatil.newsapp.di.module.ActivityModule
import com.vibhorpatil.newsapp.ui.base.UiState
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadlineActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class LanguageListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageListBinding
    @Inject
    lateinit var adapter: LanguageListAdapter

    @Inject
    lateinit var viewModel: LanguageListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        setUpObserver()
        setupClickListener()
    }

    private fun injectDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    private fun setUpUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect{
                    when(it){
                        is UiState.Success  -> {
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                        }
                        is UiState.Loading  -> {
                            binding.recyclerView.visibility = View.GONE
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is UiState.Error  -> {
                            Toast.makeText(this@LanguageListActivity, it.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupClickListener() {
        adapter.setOnCountryClickListener { language ->
            val intent = Intent(this, TopHeadlineActivity::class.java)
            intent.putExtra("language_id", language.languageID)
            startActivity(intent)
        }
    }

    private fun renderList(list: List<Language>) {
        adapter.addData(ArrayList(list))
    }


}