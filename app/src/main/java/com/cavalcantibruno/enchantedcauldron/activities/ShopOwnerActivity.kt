package com.cavalcantibruno.enchantedcauldron.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cavalcantibruno.enchantedcauldron.R
import com.cavalcantibruno.enchantedcauldron.databinding.ActivityShopOwnerBinding
import com.cavalcantibruno.enchantedcauldron.fragments.PurchaseHistoryFragment
import com.cavalcantibruno.enchantedcauldron.fragments.ShopFragment
import com.cavalcantibruno.enchantedcauldron.fragments.StockFragment
import com.google.android.material.navigation.NavigationBarView

class ShopOwnerActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityShopOwnerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.navBar.setOnItemSelectedListener{ menuItem ->
            when(menuItem.itemId){
                R.id.shop -> {
                    val shopFragment = ShopFragment()
                    openFragment(shopFragment)
                    true
                }
                R.id.shopStock -> {
                    openFragment(StockFragment())
                    true
                }
                R.id.addPotion ->{
                    val intent = Intent(this,RegisterPotionActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.purchaseHistory -> {
                    openFragment(PurchaseHistoryFragment())
                    true
                }
                else -> {false}
            }
        }

    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentShopOwner,fragment)
            .commit()
    }


}