package com.vibhorpatil.newsapp.ui.country

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vibhorpatil.newsapp.R
import com.vibhorpatil.newsapp.databinding.ActivityCountryListBinding

class CountryListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}