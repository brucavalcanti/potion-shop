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
        val insertPotionTest = PotionDAO(this)
        val potionList = mutableListOf<Potion>()

      /*  val potion1 = Potion(null,
            "Healing Potion","Heal Minor Injuries",
            "Potion",5.0,100,"https://cdn-icons-png.flaticon.com/512/1205/1205664.png")
        val potion2 = Potion(null,
            "Mana Potion","Restore a small quantity of mana",
            "Potion",5.0,100,"https://cdn-icons-png.flaticon.com/512/1205/1205664.png")
        val potion3 = Potion(null,
            "Stamina Potion","Restore a small quantity of stamina",
            "Potion",5.0,100,"https://cdn-icons-png.flaticon.com/512/1205/1205664.png")
        val potion4 = Potion(null,
            "Stone Skin Elixir","Makes your skin as hard as stone",
            "Elixir",15.0,100,"https://cdn-icons-png.flaticon.com/512/1205/1205664.png")
        val potion5 = Potion(null,
            "Cat Eye Elixir","Grants who consume it darkvision for 10 minutes",
            "Elixir",15.0,100,"https://cdn-icons-png.flaticon.com/512/1205/1205664.png")
        val potion6 = Potion(null,
            "Red Oak Flower","Major ingredient to healing potions",
            "Herb",2.25,300,"https://cdn-icons-png.flaticon.com/512/1205/1205664.png")


        potionList.add(potion1)
        potionList.add(potion2)
        potionList.add(potion3)
        potionList.add(potion4)
        potionList.add(potion5)
        potionList.add(potion6)
        potionList.forEach {item ->
            insertPotionTest.createPotion(item)
        }
*/


        binding.btnOpen.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}