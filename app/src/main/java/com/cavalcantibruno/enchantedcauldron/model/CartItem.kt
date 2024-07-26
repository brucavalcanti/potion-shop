package com.cavalcantibruno.enchantedcauldron.model

import java.io.Serializable

data class CartItem(

    val potion: Potion?,
    var quantity:Int

):Serializable