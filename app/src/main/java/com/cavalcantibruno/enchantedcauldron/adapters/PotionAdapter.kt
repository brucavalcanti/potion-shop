package com.cavalcantibruno.enchantedcauldron.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.cavalcantibruno.enchantedcauldron.databinding.ShopItemBinding

import com.cavalcantibruno.enchantedcauldron.model.Potion
import com.squareup.picasso.Picasso

class PotionAdapter (private var potionList:List<Potion>,private val click:(Potion)->Unit)
    :RecyclerView.Adapter<PotionAdapter.PotionViewHolder>() {

        //var potionList = emptyList<Potion>()

        inner class PotionViewHolder(itemBinding: ShopItemBinding)
            :RecyclerView.ViewHolder(itemBinding.root){
            private val binding:ShopItemBinding

            init {
                binding = itemBinding
            }

            fun binding(potionItem:Potion)
            {
                val image = potionItem.potionImage.toUri()
                Log.d("PotionAdapter", "binding: ${image} ")
                Picasso.get().load(image).resize(300,300).into(binding.itemImage)
                binding.itemName.text = potionItem.potionName
                binding.itemCard.setOnClickListener {
                    click(potionItem)
                }
            }
        }

    fun reloadList(lista:List<Potion>)
    {
        this.potionList = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PotionViewHolder {
        val potionItemBinding = ShopItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false)
        return PotionViewHolder(potionItemBinding)
    }

    override fun getItemCount(): Int {
        return potionList.size
    }

    override fun onBindViewHolder(holder: PotionViewHolder, position: Int) {
        val potion = potionList[position]
        holder.binding(potion)
    }
}