package com.example.exchangeratesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangeratesapp.adapters.TopRatesAdapter
import com.example.exchangeratesapp.base.BaseFragment
import com.example.exchangeratesapp.databinding.FavouriteRatesFragmentLayoutBinding

class FavouriteRatesFragment : BaseFragment<FavouriteRatesFragmentLayoutBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FavouriteRatesFragmentLayoutBinding
        get() = FavouriteRatesFragmentLayoutBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
    }
    private fun setUpRecycler (){
        binding.favouritesRv.layoutManager = LinearLayoutManager(requireContext())
        val adapter = TopRatesAdapter(requireContext() , makeFavourite = { null } , makeUnFavourite = { rateId -> makeUnfavourite(rateId) })
        binding.favouritesRv.adapter = adapter

        viewModel.favouriteRates.observe(viewLifecycleOwner){
                adapter.addItems(it)
        }
    }

    private fun makeUnfavourite(rateId: Int) {
        viewModel.makeFavourite(rateId)
    }
}