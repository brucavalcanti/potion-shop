package com.cavalcantibruno.enchantedcauldron.model

import java.io.Serializable

data class Potion(
    val potionId: Int?,
    val potionName:String,
    val potionDescription:String,
    val category:String,
    val potionPrice:Double,
    val stockQuantity:Int,
    val potionImage:String
):Serializable
