package com.vibhorpatil.newsapp.ui.newscriteria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vibhorpatil.newsapp.domain.model.NewsCriteria
import com.vibhorpatil.newsapp.databinding.ItemNewsCriteriaBinding
import javax.inject.Inject
import javax.inject.Named

class NewsCriteriaAdapter @Inject constructor(
    @Named("newsCriteriaList")  private val list: ArrayList<NewsCriteria>
) : RecyclerView.Adapter<NewsCriteriaAdapter.NewsCriteriaViewHolder>() {

    private var listener: ((NewsCriteria) -> Unit)? = null

    fun setOnItemClickListener(l: (NewsCriteria) -> Unit) {
        listener = l
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): NewsCriteriaViewHolder {
        return NewsCriteriaViewHolder(
            ItemNewsCriteriaBinding
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

    class NewsCriteriaViewHolder(private val binding: ItemNewsCriteriaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsCriteria: NewsCriteria, listener: ((NewsCriteria) -> Unit)?) {
            binding.tvNewsSourceName.text = newsCriteria.value
            itemView.setOnClickListener {
                listener?.invoke(newsCriteria)
            }
        }
    }
}