package com.cavalcantibruno.enchantedcauldron.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cavalcantibruno.enchantedcauldron.activities.AdventurerActivity
import com.cavalcantibruno.enchantedcauldron.databinding.CartItemBinding
import com.cavalcantibruno.enchantedcauldron.model.CartItem
import com.squareup.picasso.Picasso

class CartAdapter(private var cartList:List<CartItem>)
    :RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemBinding: CartItemBinding)
        :RecyclerView.ViewHolder(itemBinding.root){

        val binding:CartItemBinding

        init{
            binding = itemBinding
        }
    }

    fun reloadList(list:List<CartItem>)
    {
        this.cartList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val cartItemBinding = CartItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(cartItemBinding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val itemCart = cartList[position]
        val image = itemCart.potion?.potionImage
        Picasso.get().load(image).resize(120,120).into(holder.binding.cartItemImage)
        holder.binding.cartItemName.text = itemCart.potion?.potionName
        holder.binding.cartItemPrice.text = itemCart.potion?.potionPrice.toString()
        holder.binding.cartItemQuantity.text = itemCart.quantity.toString()
        holder.binding.cartItemAdd.setOnClickListener {
            if(itemCart.quantity < itemCart.potion?.stockQuantity!!)
                itemCart.quantity++
            reloadList(cartList)
            AdventurerActivity.AdventurerCart.calculateTotalCartPrice(cartList)
        }
        holder.binding.cartItemSub.setOnClickListener {
            if(itemCart.quantity>1)
               itemCart.quantity--
            reloadList(cartList)
            AdventurerActivity.AdventurerCart.totalPrice = 0.0
        }
        holder.binding.cartItemDelete.setOnClickListener {
            AdventurerActivity.AdventurerCart.adventurerCart.remove(itemCart)
            reloadList(cartList)
            AdventurerActivity.AdventurerCart.totalPrice = 0.0
        }
        //holder.binding(itemCart)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

}