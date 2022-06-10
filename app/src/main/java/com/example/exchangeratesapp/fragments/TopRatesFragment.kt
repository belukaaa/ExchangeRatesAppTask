package com.example.exchangeratesapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangeratesapp.adapters.TopRatesAdapter
import com.example.exchangeratesapp.base.BaseFragment
import com.example.exchangeratesapp.utils.constants
import com.example.exchangeratesapp.databinding.TopRatesFragmentLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatesFragment : BaseFragment<TopRatesFragmentLayoutBinding>() {

    private var sortRatesBy = ""

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> TopRatesFragmentLayoutBinding
        get() = TopRatesFragmentLayoutBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.topRatesRecycler.layoutManager = LinearLayoutManager(requireContext())
        val adapter = TopRatesAdapter(requireContext() , makeFavourite = { rateId -> makeFavourite(rateId) } , makeUnFavourite = { rateId -> makeUnfavourite(rateId) })
        binding.topRatesRecycler.adapter = adapter

        viewModel.sortBy.observe(viewLifecycleOwner){
            Log.i("reggg" , "this is fragment $it")
            sortRatesBy = it
            when (it) {
                constants.SORT_BY_VALUE_ASC -> {
                    viewModel.sortedRatesByValueAsc.observe(viewLifecycleOwner){it1->
                        adapter.addItems(it1)
                    }
                }
                constants.SORT_BY_VALUE_DESC -> {
                    viewModel.sortedRatesByValueDesc.observe(viewLifecycleOwner){ it1 ->
                        adapter.addItems(it1)
                    }
                }
                constants.SORT_BY_NAME_ASC -> {
                    viewModel.sortedRatesByNameDesc.observe(viewLifecycleOwner){ it1 ->
                        adapter.addItems(it1)
                    }
                }
                else -> {
                    viewModel.sortedRatesByNameAsc.observe(viewLifecycleOwner){ it1 ->
                        adapter.addItems(it1)
                    }
                }
            }
        }

    }

    private fun makeUnfavourite(rateId: Int) {
        viewModel.makeFavourite(rateId)
    }

    private fun makeFavourite(rateId: Int) {

        viewModel.makeUnFavourite(rateId)
    }
}