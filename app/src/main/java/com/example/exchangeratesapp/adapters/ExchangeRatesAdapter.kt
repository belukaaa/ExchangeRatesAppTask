package com.example.exchangeratesapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.exchangeratesapp.fragments.FavouriteRatesFragment
import com.example.exchangeratesapp.fragments.TopRatesFragment

class ExchangeRatesAdapter(fa: FragmentManager): FragmentPagerAdapter(fa) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TopRatesFragment()
            1 -> FavouriteRatesFragment()
            else -> TopRatesFragment()
        }
    }
}