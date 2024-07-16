package com.cavalcantibruno.enchantedcauldron.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.cavalcantibruno.enchantedcauldron.R
import com.cavalcantibruno.enchantedcauldron.database.PotionDAO
import com.cavalcantibruno.enchantedcauldron.databinding.ActivityPotionDetailBinding
import com.cavalcantibruno.enchantedcauldron.model.Potion
import com.squareup.picasso.Picasso
import kotlin.math.log

class PotionDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPotionDetailBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val potionItem = intent.getSerializableExtra("shopItem",Potion::class.java)
        Log.d("PotionDetailActivity", "onCreate: Received from the intent = $potionItem ")

        with(binding)
        {
            Picasso.get().load(potionItem?.potionImage).resize(512,512).into(detailedPotionImage)
            detailedPotionName.text= potionItem?.potionName
            detailedPotionPrice.text=potionItem?.potionPrice.toString()
            detailedPotionDescription.text= potionItem?.potionDescription

            btnBack.setOnClickListener {
                Log.i("PotionDetailActivity","btnBackSetOnClickListener: Closing the activity")
                finish()
            }
            btnDelete.setOnClickListener {
                if (potionItem != null) {
                    potionItem.potionId?.let { id -> potionDispose(id)
                    }
                }
            }

            btnUpdateItem.setOnClickListener {
                val intent = Intent(this@PotionDetailActivity,RegisterPotionActivity::class.java)
                intent.putExtra("potionUpdate",potionItem)
                Log.d("PotionDetailActivity", "btnUpdateItem: $potionItem")
                startActivity(intent)
                Log.i("PotionDetailActivity",
                    "btnUpdateItem: startActivity called, closing the activity")
                finish()
            }
        }


    }

    //Function to make a double check on the item delete
    private fun potionDispose(id: Int) {
        val alertBuilder = AlertDialog.Builder(this@PotionDetailActivity)
        alertBuilder.setTitle("Potion Dispose")
        alertBuilder.setMessage("Do you really want to throw away this potion?")
        alertBuilder.setPositiveButton("Yes"){ _, _ ->
            val potionDAO = PotionDAO(this@PotionDetailActivity)
             potionDAO.removePotion(id)
            Log.i("PotionDetailActivity", "potionDispose: Remove item Called ")
             finish()
        }
        alertBuilder.setNegativeButton("No"){ _, _ -> }
        alertBuilder.create().show()
    }

}