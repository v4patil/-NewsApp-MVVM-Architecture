package com.vibhorpatil.newsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vibhorpatil.newsapp.databinding.ActivityMainBinding
import com.vibhorpatil.newsapp.ui.country.CountryListActivity
import com.vibhorpatil.newsapp.ui.newssource.NewsSourceActivity
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadlineActivity

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
            val intent = Intent(this, TopHeadlineActivity::class.java);
            startActivity(intent)
        }

        activityMainBinding.btnNewsSource.setOnClickListener {
            val intent = Intent(this, NewsSourceActivity::class.java);
            startActivity(intent)
        }

        activityMainBinding.btnCountries.setOnClickListener {
            val intent = Intent(this, CountryListActivity::class.java);
            startActivity(intent)
        }
    }
}