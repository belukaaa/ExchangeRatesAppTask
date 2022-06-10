package com.example.exchangeratesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratesapp.R
import com.example.exchangeratesapp.database.entity.LatestExchangeRatesEntity
import com.example.exchangeratesapp.databinding.TopExchangeRatesRwItemBinding

class TopRatesAdapter(private val context : Context, val makeFavourite: (rateId: Int) -> Unit, val makeUnFavourite: (rateId: Int) -> Unit) : RecyclerView.Adapter<TopRatesAdapter.TopRatesViewHolder>() {

    private var itemList = ArrayList<LatestExchangeRatesEntity>()

    inner class TopRatesViewHolder(val view : TopExchangeRatesRwItemBinding) :RecyclerView.ViewHolder(view.root){
        fun bind(model: LatestExchangeRatesEntity) {
            view.nameOfRate.text = model.rateName
            view.valueOfRate.text = model.rateValue.toString()

            if (model.isFavourite){
                view.favouritesButton.setImageResource(R.drawable.favorite_image)
            }else{
                view.favouritesButton.setImageResource(R.drawable.unfavourite_image)
            }
        }
        fun editRate(rate: LatestExchangeRatesEntity){
            view.favouritesButton.setOnClickListener {
                if (rate.isFavourite){
                    makeUnFavourite(rate.id)
                }else {
                    makeFavourite(rate.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatesAdapter.TopRatesViewHolder {
        return TopRatesViewHolder(TopExchangeRatesRwItemBinding.inflate(LayoutInflater.from(context) , parent , false))
    }

    override fun onBindViewHolder(holder: TopRatesAdapter.TopRatesViewHolder, position: Int) {
        val item = itemList[position]

        holder.bind(item)
        holder.editRate(item)
    }

    override fun getItemCount(): Int = itemList.size

    fun addItems(list : List<LatestExchangeRatesEntity>){
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
    fun clearList(){
        itemList.clear()
        notifyDataSetChanged()
    }
}