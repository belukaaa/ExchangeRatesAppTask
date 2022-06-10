package com.example.exchangeratesapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.example.exchangeratesapp.adapters.ExchangeRatesAdapter
import com.example.exchangeratesapp.databinding.ActivityMainBinding
import com.example.exchangeratesapp.databinding.SortingLayoutBinding
import com.example.exchangeratesapp.utils.constants
import com.example.exchangeratesapp.utils.setInvisible
import com.example.exchangeratesapp.utils.setVisible
import com.example.exchangeratesapp.viewmodel.ExchangeRatesViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel : ExchangeRatesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupPager()
        observers()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.refresh.setOnClickListener {
            viewModel.refreshData()
        }
        binding.sorting.setOnClickListener {
            val binding = SortingLayoutBinding.inflate(LayoutInflater.from(this))
            val sortingDialog = Dialog(this,R.style.DialogStyle)
            sortingDialog.setContentView(binding.root)
            binding.sortByNameAsc.setOnClickListener {
                viewModel.sortBy.postValue(constants.SORT_BY_NAME_ASC)
                sortingDialog.dismiss()
            }
            binding.sortByNameDesc.setOnClickListener {
                viewModel.sortBy.postValue(constants.SORT_BY_NAME_DESC)
                sortingDialog.dismiss()
            }
            binding.sortByValueAsc.setOnClickListener {
                viewModel.sortBy.postValue(constants.SORT_BY_VALUE_ASC)
                sortingDialog.dismiss()
            }
            binding.sortByValueDesc.setOnClickListener {
                viewModel.sortBy.postValue(constants.SORT_BY_VALUE_DESC)
                sortingDialog.dismiss()
            }
            sortingDialog.show()
        }
    }

    private fun setupPager() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.top_rates)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.favourites)))
        val pagerAdapter = ExchangeRatesAdapter(supportFragmentManager)
        binding.pager.adapter = pagerAdapter
        binding.pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener(OnTabSelectedListener())
    }
    private fun observers() {
        viewModel.rates.observe(this){
            Log.i("requesterror" , "$it")
        }
        viewModel.responseRates.observe(this){
            Log.d("requesterrorbratuxa" , "$it")
        }
        viewModel.sortBy.observe(this){
            binding.sortedBy.text = "Sorted by $it"
        }
    }
    inner class OnTabSelectedListener : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            binding.pager.currentItem = tab.position

            when (tab.position) {
                0 -> {
                    binding.refresh.setVisible()
                    binding.sortedBy.setVisible()
                    binding.sorting.setVisible()
                Log.i("requesterrorbratuxa", "position 0")
                }
                1 -> {
                    binding.refresh.setInvisible()
                    binding.sortedBy.setInvisible()
                    binding.sorting.setInvisible()
                    Log.i("requesterrorbratuxa", "position 1")
                }
                2 -> {

                }
            }
        }
        override fun onTabUnselected(tab: TabLayout.Tab) {}
        override fun onTabReselected(tab: TabLayout.Tab) {}
    }

}