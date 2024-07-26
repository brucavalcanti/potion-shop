package com.cavalcantibruno.enchantedcauldron.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cavalcantibruno.enchantedcauldron.adapters.StockAdapter
import com.cavalcantibruno.enchantedcauldron.database.DatabaseHelper
import com.cavalcantibruno.enchantedcauldron.database.PotionDAO
import com.cavalcantibruno.enchantedcauldron.databinding.FragmentStockBinding
import com.cavalcantibruno.enchantedcauldron.model.Potion

class StockFragment: Fragment() {

    private val binding by lazy {
        FragmentStockBinding.inflate(layoutInflater)
    }

    private var stockList = emptyList<Potion>()
    private var stockAdapter: StockAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val stockList = context?.let { PotionDAO(it).readPotion(DatabaseHelper.SHOP_TABLE) }

        stockAdapter = stockList?.let { StockAdapter(it) }
        binding.rvStock.adapter = stockAdapter
        binding.rvStock.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        updateStock()
    }

    private fun updateStock()
    {
        stockList = context?.let {
            PotionDAO(it).readPotion("${DatabaseHelper.SHOP_TABLE}") }!!
        stockAdapter?.reloadStock(stockList)
    }

}