package com.cavalcantibruno.enchantedcauldron.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.cavalcantibruno.enchantedcauldron.adapters.PotionAdapter
import com.cavalcantibruno.enchantedcauldron.activities.AdventurerItemDetailActivity
import com.cavalcantibruno.enchantedcauldron.database.DatabaseHelper
import com.cavalcantibruno.enchantedcauldron.database.PotionDAO
import com.cavalcantibruno.enchantedcauldron.databinding.FragmentAdventurerShopBinding
import com.cavalcantibruno.enchantedcauldron.model.Potion

class AdventurerShopFragment : Fragment() {

    private val binding by lazy{
        FragmentAdventurerShopBinding.inflate(layoutInflater)
    }

    private val CLASS_TAG="AdventurerShopFragment"



    private var potionList = emptyList<Potion>()
    private var potionAdapter: PotionAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        potionList = context?.let { PotionDAO(it).readPotion(DatabaseHelper.SHOP_TABLE) }!!
        Log.d(CLASS_TAG, "onCreateView: $potionList ")

        potionAdapter = PotionAdapter(potionList){item ->
            val intent = Intent(context,AdventurerItemDetailActivity::class.java)
            Log.i(CLASS_TAG, "onCreateView: Intent Called, starting AdventurerItemDetailActivity")
            intent.putExtra("shopItem",item)
            Log.d(CLASS_TAG, "onCreateView: $item ")
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