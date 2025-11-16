package com.vibhorpatil.newsapp.ui.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vibhorpatil.newsapp.data.model.Country
import com.vibhorpatil.newsapp.databinding.ItemCountryNameBinding
import com.vibhorpatil.newsapp.di.module.ActivityScope
import javax.inject.Inject

@ActivityScope
class CountryListAdapter @Inject constructor(private val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    private var listener: ((Country) -> Unit)? = null

    fun setOnCountryClickListener(l: (Country) -> Unit) {
        listener = l
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            ItemCountryNameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countryList[position], listener)
    }

    fun addData(list: ArrayList<Country>) {
        countryList.addAll(list)
    }


    class CountryViewHolder(private val binding: ItemCountryNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country, listener: ((Country) -> Unit)?) {
            binding.tvCountryName.text = country.countryName
            itemView.setOnClickListener {
                listener?.invoke(country)
            }
        }

    }
}