package com.example.uihomework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uihomework.databinding.ItemHighPollutionBinding
import com.example.uihomework.model.Record

class VerticalPollutionsAdapter: ListAdapter<Record, VerticalPollutionsAdapter.MyViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHighPollutionBinding.inflate(layoutInflater, parent, false)
        binding.ivMoreButton.setOnClickListener {
            Toast.makeText(it.context, "click button", Toast.LENGTH_LONG).show()
        }
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setRecord(getItem(position))
    }

    class MyViewHolder(private val binding: ItemHighPollutionBinding): RecyclerView.ViewHolder(binding.root) {
        fun setRecord(data : Record) {
            binding.tvSiteId.text = data.siteId
            binding.tvSiteName.text = data.siteName
            binding.tvPm25.text = data.pm25
            binding.tvCountry.text = data.county
            binding.tvStatus.text = if (data.status == "良好") "The status is good, we want to go out to have fun" else data.status
            binding.ivMoreButton.visibility = if (data.status == "良好") View.GONE else View.VISIBLE
        }
    }
}