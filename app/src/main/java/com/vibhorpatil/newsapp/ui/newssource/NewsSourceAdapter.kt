package com.vibhorpatil.newsapp.ui.newssource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vibhorpatil.newsapp.data.model.NewsSource
import com.vibhorpatil.newsapp.databinding.ItemNewsSourceBinding
import com.vibhorpatil.newsapp.di.module.ActivityScope
import javax.inject.Inject

@ActivityScope
class NewsSourceAdapter @Inject constructor(private val sourceList: ArrayList<NewsSource>) :
    RecyclerView.Adapter<NewsSourceAdapter.NewsSourceDataHolder>() {

    private var listener: ((NewsSource) -> Unit)? = null

    fun setOnCountryClickListener(l: (NewsSource) -> Unit) {
        listener = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourceDataHolder {
        return NewsSourceDataHolder(
            ItemNewsSourceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = sourceList.size

    override fun onBindViewHolder(holder: NewsSourceDataHolder, position: Int) {
        holder.bind(sourceList[position], listener)
    }

    fun addData(list: ArrayList<NewsSource>){
        sourceList.addAll(list)
    }

    class NewsSourceDataHolder(private val binding: ItemNewsSourceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsSource: NewsSource, listener: ((NewsSource) -> Unit)?) {
            binding.tvNewsSourceName.text = newsSource.sourceName
            itemView.setOnClickListener {
                listener?.invoke(newsSource)
            }
        }

    }
}