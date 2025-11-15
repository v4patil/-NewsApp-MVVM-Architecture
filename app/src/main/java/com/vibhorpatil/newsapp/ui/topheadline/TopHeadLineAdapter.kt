package com.vibhorpatil.newsapp.ui.topheadline

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.databinding.TopHeadlineItemLayoutBinding
import androidx.browser.customtabs.CustomTabsIntent
import javax.inject.Inject


class TopHeadLineAdapter @Inject constructor(private val articleList: ArrayList<Article>)
    : RecyclerView.Adapter<TopHeadLineAdapter.TopHeadLineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadLineViewHolder {
        return TopHeadLineViewHolder(TopHeadlineItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount() = articleList.size

    override fun onBindViewHolder(holder: TopHeadLineViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    fun addData(list: ArrayList<Article>){
        articleList.addAll(list)
    }

    class TopHeadLineViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(article: Article){
                binding.tvTitle.text = article.title
                binding.tvDescription.text = article.description
                binding.tvSource.text = article.source.name
                Glide.with(binding.ivBanner.context)
                    .load(article.imageUrl)
                    .into(binding.ivBanner)

                itemView.setOnClickListener{
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(it.context, Uri.parse(article.url))

                }
            }
    }
}