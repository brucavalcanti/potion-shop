package com.cavalcantibruno.enchantedcauldron.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.cavalcantibruno.enchantedcauldron.R
import com.cavalcantibruno.enchantedcauldron.databinding.ActivityAdventurerItemDetailBinding
import com.cavalcantibruno.enchantedcauldron.model.CartItem
import com.cavalcantibruno.enchantedcauldron.model.Potion
import com.squareup.picasso.Picasso

class AdventurerItemDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdventurerItemDetailBinding.inflate(layoutInflater)
    }

    private var quantity = 1



    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val shopItemDetails = intent.getSerializableExtra("shopItem",Potion::class.java)

        with(binding) {
            Picasso.get().load(shopItemDetails?.potionImage)
                .resize(512, 512).into(detailedPotionImage)

            detailedPotionName.text= shopItemDetails?.potionName
            detailedPotionPrice.text=shopItemDetails?.potionPrice.toString()
            detailedPotionDescription.text = shopItemDetails?.potionDescription
            textQuantity.text= quantity.toString()
            btnBack.setOnClickListener {
                intent.removeExtra("cart")
                finish()
            }
            btnAddQuantity.setOnClickListener {
                if(quantity< shopItemDetails?.stockQuantity!!) {
                    quantity += 1
                } else {
                    quantity= shopItemDetails?.stockQuantity
                    message("Maximum Stock quantity reached")
                }
                textQuantity.text= quantity.toString()
                Log.d("AdventurerItemDetailActivity", "btnAddQuantity: $quantity")
            }

            btnRemoveQuantity.setOnClickListener {
                if(quantity>=2) quantity-=1 else quantity=1
                textQuantity.text= quantity.toString()
                Log.d("AdventurerItemDetailActivity", "btnAddQuantity: $quantity")
            }

            btnAddToCart.setOnClickListener {
                val adventurerCart = CartItem(shopItemDetails,quantity)
                AdventurerActivity.AdventurerCart.addCartItem(adventurerCart)
                message("Item added to cart")
                finish()
            }

        }


    }

    //Auxiliary functions to a more clean code

    private fun message(text:String){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }
}