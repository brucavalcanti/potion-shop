package com.cavalcantibruno.enchantedcauldron.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cavalcantibruno.enchantedcauldron.R
import com.cavalcantibruno.enchantedcauldron.adapters.CartAdapter
import com.cavalcantibruno.enchantedcauldron.databinding.ActivityAdventurerCartBinding
import com.cavalcantibruno.enchantedcauldron.model.CartItem

class AdventurerCartActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdventurerCartBinding.inflate(layoutInflater)
    }

    private var cartAdapter:CartAdapter? = null
    private val cartList = AdventurerActivity.AdventurerCart.adventurerCart
    private var finalCart = ""
    private var totalPrice = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        cartAdapter = CartAdapter(AdventurerActivity.AdventurerCart.adventurerCart)
        binding.rvCart.adapter = cartAdapter
        binding.rvCart.layoutManager = LinearLayoutManager(this)

        binding.totalPrice.text = AdventurerActivity
            .AdventurerCart.calculateTotalCartPrice(cartList).toString()

        binding.btnBuy.setOnClickListener {
            cartList.forEach { item->
                finalCart+="${item.potion?.potionName} -- ${item.potion?.potionPrice} x ${item.quantity}\n"
            }
            totalPrice=AdventurerActivity.AdventurerCart.calculateTotalCartPrice(cartList)
            Log.d("AdventurerCartActivity", "Here is your basket: \n $finalCart \n Total Price: $totalPrice")
            finalCart=""
            cartList.removeAll(cartList)
            Toast.makeText(this,"Purchase Confirmed",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}