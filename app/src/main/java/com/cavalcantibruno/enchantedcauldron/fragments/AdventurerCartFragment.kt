package com.cavalcantibruno.enchantedcauldron.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cavalcantibruno.enchantedcauldron.adapters.CartAdapter
import com.cavalcantibruno.enchantedcauldron.activities.AdventurerActivity
import com.cavalcantibruno.enchantedcauldron.databinding.FragmentAdventurerCartBinding

class AdventurerCartFragment : Fragment() {

    private val binding by lazy {
        FragmentAdventurerCartBinding.inflate(layoutInflater)
    }
    var listCart = AdventurerActivity.AdventurerCart.adventurerCart
    var totalPrice = 0.0
    private var cartAdapter: CartAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        cartAdapter = CartAdapter(AdventurerActivity.AdventurerCart.adventurerCart)
        binding.rvCart.adapter = cartAdapter
        binding.rvCart.layoutManager = LinearLayoutManager(context)
        calculatePrice()
        return binding.root

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return super.onContextItemSelected(item)
    }

    fun calculatePrice(){
        listCart.forEach { cartItem->
            totalPrice+= (cartItem.potion?.potionPrice?.times(cartItem.quantity)!!)
        }

        binding.totalPrice.text=totalPrice.toString()
        totalPrice=0.0
    }

}