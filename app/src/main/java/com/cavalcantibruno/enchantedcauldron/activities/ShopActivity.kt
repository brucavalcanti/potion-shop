package com.cavalcantibruno.enchantedcauldron.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.cavalcantibruno.enchantedcauldron.PotionAdapter
import com.cavalcantibruno.enchantedcauldron.database.DatabaseHelper
import com.cavalcantibruno.enchantedcauldron.database.PotionDAO
import com.cavalcantibruno.enchantedcauldron.databinding.ActivityShopBinding
import com.cavalcantibruno.enchantedcauldron.model.Potion
import com.squareup.picasso.Picasso

class ShopActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityShopBinding.inflate(layoutInflater)
    }
    
    private var potionList = emptyList<Potion>()
    private var potionAdapter: PotionAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Picasso.get().setLoggingEnabled(true)

        binding.addItemButton.setOnClickListener {
            val intent = Intent(this, RegisterPotionActivity::class.java)
            startActivity(intent)

        }

        potionList = PotionDAO(this).readPotion("${DatabaseHelper.SHOP_TABLE}")

        potionList.forEach {item ->
            Log.d("ShopActivity", "onCreate: ${item.potionName} -> ${item.potionDescription}")
        }

        potionAdapter = PotionAdapter(potionList){item->
            val intent = Intent(this@ShopActivity,PotionDetailActivity::class.java)
            intent.putExtra("shopItem",item)
            startActivity(intent)
        }
        binding.rvShop.adapter = potionAdapter
        binding.rvShop.layoutManager = GridLayoutManager(this,3)

    }

    //Function to update the screen with the new list, without the need to reload the app
    private fun updatePotionShop()
    {
        potionList = PotionDAO(this).readPotion("${DatabaseHelper.SHOP_TABLE}")
        potionAdapter?.reloadList(potionList)
    }

    /*fun deleteItem(id:Int)
    {
        val potionDAO = PotionDAO(this)
        potionDAO.removePotion(id)
        updatePotionShop()
    }*/

    override fun onStart() {
        super.onStart()
        updatePotionShop()
    }

}