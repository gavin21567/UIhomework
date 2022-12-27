package com.example.uihomework.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.uihomework.R
import com.example.uihomework.adapter.HorizontalPollutionsAdapter
import com.example.uihomework.adapter.VerticalPollutionsAdapter
import com.example.uihomework.databinding.ActivityMainBinding
import com.example.uihomework.viewmodel.AirPollutionViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var lowPollutionsAdapter: HorizontalPollutionsAdapter
    private lateinit var highPollutionsAdapter: VerticalPollutionsAdapter
    private lateinit var searchAdapter: VerticalPollutionsAdapter
    private lateinit var viewModel: AirPollutionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initList()

        binding.ivSearch.setOnClickListener {
            binding.searchBarLayout.visibility = View.VISIBLE
            binding.searchResultLayout.visibility = View.VISIBLE
        }

        binding.ivBack.setOnClickListener {
            binding.searchBarLayout.visibility = View.INVISIBLE
            binding.searchResultLayout.visibility = View.INVISIBLE
        }

        binding.inputField.setOnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search(binding.inputField.text.toString())
                true
            } else {
                false
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AirPollutionViewModel::class.java)
        viewModel.getLowPollutionListObservable().observe(this) {
            if (it == null) {
                Toast.makeText(this@MainActivity, "no result found...", Toast.LENGTH_LONG).show()
            } else {
                lowPollutionsAdapter.submitList(it.toMutableList())
            }
        }

        viewModel.getHighPollutionListObservable().observe(this) {
            if (it == null) {
                Toast.makeText(this@MainActivity, "no result found...", Toast.LENGTH_LONG).show()
            } else {
                highPollutionsAdapter.submitList(it.toMutableList())
            }
        }
        viewModel.getSearchListObservable().observe(this) {
            if (it == null || it.isEmpty()) {
                val searchKey: String = binding.inputField.text.toString()
                binding.tvSearchHint.visibility = View.VISIBLE
                searchAdapter.submitList(it.toMutableList())
                binding.tvSearchHint.text = getString(R.string.search_result_empty_hint, searchKey)
            } else {
                binding.tvSearchHint.visibility = View.INVISIBLE
                searchAdapter.submitList(it.toMutableList())
            }
        }
        viewModel.getAirPollution()
    }

    private fun initList() {
        lowPollutionsAdapter = HorizontalPollutionsAdapter()
        highPollutionsAdapter = VerticalPollutionsAdapter()
        searchAdapter = VerticalPollutionsAdapter()
        binding.topList.adapter = lowPollutionsAdapter
        binding.bottomList.adapter = highPollutionsAdapter
        binding.searchList.adapter = searchAdapter
    }
}