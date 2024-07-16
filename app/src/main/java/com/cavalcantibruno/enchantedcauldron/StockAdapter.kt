package com.cavalcantibruno.enchantedcauldron

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cavalcantibruno.enchantedcauldron.databinding.StockItemBinding
import com.cavalcantibruno.enchantedcauldron.model.Potion
import com.squareup.picasso.Picasso

class StockAdapter(private var stockList:List<Potion>)
    :RecyclerView.Adapter<StockAdapter.StockViewHolder> (){

    inner class StockViewHolder(itemBinding: StockItemBinding)
        :RecyclerView.ViewHolder(itemBinding.root){
            private val binding:StockItemBinding

            init {
                binding = itemBinding
            }

        fun binding(stockItem:Potion){
            binding.stockItemName.text = stockItem.potionName
            binding.stockQuantity.text = stockItem.stockQuantity.toString()
            Picasso.get().load(stockItem.potionImage).resize(200,200).into(binding.stockImage)
        }

    }

    fun reloadStock(stockListReload:List<Potion>){
        this.stockList = stockListReload
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StockAdapter.StockViewHolder {
        val stockItemBinding = StockItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return StockViewHolder(stockItemBinding)
    }

    override fun onBindViewHolder(holder: StockAdapter.StockViewHolder, position: Int) {
        val item = stockList[position]
        holder.binding(item)
    }


    override fun getItemCount(): Int {
       return stockList.size
    }
}