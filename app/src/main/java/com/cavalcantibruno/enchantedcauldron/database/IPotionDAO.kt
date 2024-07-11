package com.cavalcantibruno.enchantedcauldron.database

import com.cavalcantibruno.enchantedcauldron.model.Potion

interface IPotionDAO {

    fun createPotion( potion: Potion):Boolean
    fun readPotion(databaseName:String):List<Potion>
    fun updatePotion(potion: Potion):Boolean
    fun removePotion(potionId:Int):Boolean

}