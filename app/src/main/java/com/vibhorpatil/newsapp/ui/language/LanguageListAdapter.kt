package com.vibhorpatil.newsapp.ui.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vibhorpatil.newsapp.data.model.Country
import com.vibhorpatil.newsapp.data.model.Language
import com.vibhorpatil.newsapp.databinding.ItemNewsSourceBinding
import com.vibhorpatil.newsapp.di.module.ActivityScope
import javax.inject.Inject

@ActivityScope
class LanguageListAdapter @Inject constructor(private val languageList: ArrayList<Language>) :
    RecyclerView.Adapter<LanguageListAdapter.LanguageViewHolder>() {

    private var listener: ((Language) -> Unit)? = null

    fun setOnCountryClickListener(l: (Language) -> Unit) {
        listener = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        return LanguageViewHolder(
            ItemNewsSourceBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind(languageList[position], listener)
    }

    fun addData(list: ArrayList<Language>){
        languageList.addAll(list)
    }

    class LanguageViewHolder(private val binding: ItemNewsSourceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language, listener: ((Language) -> Unit)?) {
            binding.tvNewsSourceName.text = language.languageName
            itemView.setOnClickListener {
                listener?.invoke(language)
            }
        }

    }

}