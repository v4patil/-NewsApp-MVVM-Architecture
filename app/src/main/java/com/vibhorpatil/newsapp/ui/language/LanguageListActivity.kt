package com.vibhorpatil.newsapp.ui.language

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.vibhorpatil.newsapp.NewsApplication
import com.vibhorpatil.newsapp.R
import com.vibhorpatil.newsapp.databinding.ActivityLanguageListBinding
import com.vibhorpatil.newsapp.di.component.DaggerActivityComponent
import com.vibhorpatil.newsapp.di.module.ActivityModule
import javax.inject.Inject

class LanguageListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageListBinding
    @Inject
    lateinit var adapter: LanguageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
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

}