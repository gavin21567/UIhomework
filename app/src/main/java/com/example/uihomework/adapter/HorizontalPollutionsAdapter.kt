package com.example.uihomework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uihomework.databinding.ItemLowPollutionBinding
import com.example.uihomework.model.Record

class HorizontalPollutionsAdapter: ListAdapter<Record, HorizontalPollutionsAdapter.MyViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLowPollutionBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setRecord(getItem(position))
    }

    class MyViewHolder(private val binding: ItemLowPollutionBinding): RecyclerView.ViewHolder(binding.root) {
        fun setRecord(data : Record) {
            binding.tvSiteId.text = data.siteId
            binding.tvSiteName.text = data.siteName
            binding.tvPm25.text = data.pm25
            binding.tvCountry.text = data.county
            binding.tvStatus.text = data.status
        }
    }

}

class DiffCallback: DiffUtil.ItemCallback<Record>() {
    override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
        return oldItem.siteName == newItem.siteName
    }
}