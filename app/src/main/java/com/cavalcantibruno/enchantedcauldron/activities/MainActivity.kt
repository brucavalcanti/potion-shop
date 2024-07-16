package com.cavalcantibruno.enchantedcauldron.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cavalcantibruno.enchantedcauldron.database.DatabaseHelper
import com.cavalcantibruno.enchantedcauldron.database.PotionDAO
import com.cavalcantibruno.enchantedcauldron.databinding.ActivityMainBinding
import com.cavalcantibruno.enchantedcauldron.model.Potion

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        DatabaseHelper(this)

        binding.btnOpen.setOnClickListener {
            val intent = Intent(this, ShopOwnerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}