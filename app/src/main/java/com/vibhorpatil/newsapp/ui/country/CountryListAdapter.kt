package com.vibhorpatil.newsapp.ui.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vibhorpatil.newsapp.data.model.Country
import com.vibhorpatil.newsapp.databinding.ItemCountryNameBinding

class CountryListAdapter(private var countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {


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
        holder.bind(countryList[position])
    }

    fun addData(list: ArrayList<Country>) {
        countryList.addAll(list)
    }


    class CountryViewHolder(private val binding: ItemCountryNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.tvCountryName.text = country.countryName
        }

    }
}