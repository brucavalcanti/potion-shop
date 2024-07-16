package com.cavalcantibruno.enchantedcauldron.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.cavalcantibruno.enchantedcauldron.PotionAdapter
import com.cavalcantibruno.enchantedcauldron.activities.PotionDetailActivity
import com.cavalcantibruno.enchantedcauldron.database.DatabaseHelper
import com.cavalcantibruno.enchantedcauldron.database.PotionDAO
import com.cavalcantibruno.enchantedcauldron.databinding.FragmentShopBinding
import com.cavalcantibruno.enchantedcauldron.model.Potion
import com.squareup.picasso.Picasso

class ShopFragment:Fragment() {

    private val binding by lazy {
        FragmentShopBinding.inflate(layoutInflater)
    }

    private var potionList = emptyList<Potion>()
    private var potionAdapter: PotionAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Picasso.get().setLoggingEnabled(true)

        potionList = context?.let {
            PotionDAO(it).readPotion("${DatabaseHelper.SHOP_TABLE}") }!!

        potionList.forEach {item ->
            Log.d("ShopActivity", "onCreate: ${item.potionName} -> ${item.potionDescription}")
        }

        potionAdapter = PotionAdapter(potionList){ item->
            val intent = Intent(context, PotionDetailActivity::class.java)
            intent.putExtra("shopItem",item)
            startActivity(intent)
        }
        binding.rvShop.adapter = potionAdapter
        binding.rvShop.layoutManager = GridLayoutManager(context,3)


        return binding.root
    }

    private fun updatePotionShop()
    {
        potionList = context?.let {
            PotionDAO(it).readPotion("${DatabaseHelper.SHOP_TABLE}") }!!
        potionAdapter?.reloadList(potionList)
    }

    override fun onStart() {
        super.onStart()
        updatePotionShop()
    }

}