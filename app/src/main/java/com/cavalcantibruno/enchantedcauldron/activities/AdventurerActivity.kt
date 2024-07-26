package com.cavalcantibruno.enchantedcauldron.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.cavalcantibruno.enchantedcauldron.R
import com.cavalcantibruno.enchantedcauldron.databinding.ActivityAdventurerBinding
import com.cavalcantibruno.enchantedcauldron.fragments.AdventurerCartFragment
import com.cavalcantibruno.enchantedcauldron.fragments.AdventurerShopFragment
import com.cavalcantibruno.enchantedcauldron.model.CartItem
import com.cavalcantibruno.enchantedcauldron.model.Potion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdventurerActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdventurerBinding.inflate(layoutInflater)
    }

    /*Object that represents a singleton to save the actual instance of the cart,
    while the app is running*/
    object AdventurerCart {
        var totalPrice = 0.0
        var adventurerCart = mutableListOf<CartItem>()

        fun addCartItem(item:CartItem) = adventurerCart.add(item)

        fun calculateTotalCartPrice (listCart:List<CartItem>):Double{
            totalPrice = 0.0
            listCart.forEach { cartItem ->
                totalPrice += (cartItem.potion?.potionPrice?.times(cartItem.quantity)!!)
            }
            return totalPrice
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.adventurerNavBar.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.shopItem -> {
                    openFragment(AdventurerShopFragment())
                    true
                }

                R.id.basketItem -> {
                    val intent = Intent(this,AdventurerCartActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> true
            }
        }
    }

    fun generateCartQuantity (){
        var badgeCart = binding.adventurerNavBar.getOrCreateBadge(R.id.basketItem)
        badgeCart.isVisible
        badgeCart.number = AdventurerCart.adventurerCart.size

    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.adventurerShopFragment,fragment)
            .commit()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onResume() {
        generateCartQuantity()
        super.onResume()

        Log.d("AdventurerActivity", "onResume: CartInstance -> ${AdventurerCart.adventurerCart} ")

    }



}