package com.vibhorpatil.newsapp.ui.newscriteria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vibhorpatil.newsapp.data.model.NewsCriteria
import com.vibhorpatil.newsapp.databinding.ItemNewsSourceBinding
import com.vibhorpatil.newsapp.di.module.ActivityScope
import javax.inject.Inject

@ActivityScope
class NewsCriteriaAdapter @Inject constructor(
    private val list: ArrayList<NewsCriteria>
) : RecyclerView.Adapter<NewsCriteriaAdapter.NewsCriteriaViewHolder>() {

    private var listener: ((NewsCriteria) -> Unit)? = null

    fun setOnItemClickListener(l: (NewsCriteria) -> Unit) {
        listener = l
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): NewsCriteriaViewHolder {
        return NewsCriteriaViewHolder(
            ItemNewsSourceBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsCriteriaViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount() = list.size

    fun setData(list: List<NewsCriteria>) {
        this.list.addAll(list)
    }

    class NewsCriteriaViewHolder(private val binding: ItemNewsSourceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsCriteria: NewsCriteria, listener: ((NewsCriteria) -> Unit)?) {
            binding.tvNewsSourceName.text = newsCriteria.value
            itemView.setOnClickListener {
                listener?.invoke(newsCriteria)
            }
        }
    }
}