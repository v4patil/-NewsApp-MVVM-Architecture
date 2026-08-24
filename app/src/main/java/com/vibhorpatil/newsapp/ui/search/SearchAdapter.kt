package com.vibhorpatil.newsapp.ui.search

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.databinding.TopHeadlineItemLayoutBinding
import javax.inject.Inject
import javax.inject.Named

class SearchAdapter @Inject constructor(
    @Named("articleList") private val articleList: ArrayList<Article>
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            TopHeadlineItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount() = articleList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    fun addData(list: ArrayList<Article>){
        articleList.clear()
        articleList.addAll(list)
    }

    class SearchViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article){
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvSource.text = article.source.sourceName
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