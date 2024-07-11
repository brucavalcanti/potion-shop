package com.cavalcantibruno.enchantedcauldron.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.cavalcantibruno.enchantedcauldron.model.Potion

class PotionDAO(context: Context):IPotionDAO {
    val databaseHelper = DatabaseHelper(context)
    private val potionWrite  = databaseHelper.writableDatabase
    private val potionRead = databaseHelper.readableDatabase

    override fun createPotion(potion: Potion): Boolean {

        val potionContent = ContentValues()
        potionContent.put("${DatabaseHelper.ITEM_NAME}",potion.potionName)
        potionContent.put("${DatabaseHelper.ITEM_DESCRIPTION}",potion.potionDescription)
        potionContent.put("${DatabaseHelper.ITEM_CATEGORY}",potion.category)
        potionContent.put("${DatabaseHelper.ITEM_PRICE}",potion.potionPrice)
        potionContent.put("${DatabaseHelper.ITEM_STOCK}",potion.stockQuantity)
        potionContent.put("${DatabaseHelper.IMAGE_URL}",potion.potionImage)

        try
        {
            Log.d("PotionDAO", "createPotion: $potionContent")
            potionWrite.insert("${DatabaseHelper.SHOP_TABLE}",null,potionContent)
            Log.i("PotionDAO", "createPotion: Success on insert item in the database")
        }catch(e:Exception){
            e.printStackTrace()
            Log.i("PotionDAO", "createPotion: ${e.message}")
            return false
        }
        return true
    }

    override fun readPotion(databaseName: String): List<Potion> {
        val potionList = mutableListOf<Potion>()
        val sql = "SELECT * FROM ${DatabaseHelper.SHOP_TABLE}"
        val readCursor = potionRead.rawQuery(sql,null)

        val indexId = readCursor.getColumnIndex(DatabaseHelper.ITEM_ID)
        val indexName = readCursor.getColumnIndex(DatabaseHelper.ITEM_NAME)
        val indexDescription = readCursor.getColumnIndex(DatabaseHelper.ITEM_DESCRIPTION)
        val indexCategory = readCursor.getColumnIndex(DatabaseHelper.ITEM_CATEGORY)
        val indexPrice = readCursor.getColumnIndex(DatabaseHelper.ITEM_PRICE)
        val indexStock = readCursor.getColumnIndex(DatabaseHelper.ITEM_STOCK)
        val indexImage = readCursor.getColumnIndex(DatabaseHelper.IMAGE_URL)
     while (readCursor.moveToNext())
     {
         val potionId = readCursor.getInt(indexId)
         val potionName = readCursor.getString(indexName)
         val potionDescription = readCursor.getString(indexDescription)
         val potionCategory = readCursor.getString(indexCategory)
         val potionPrice = readCursor.getDouble(indexPrice)
         val potionStock = readCursor.getInt(indexStock)
         val potionImage = readCursor.getString(indexImage)
         potionList.add(
             Potion(potionId,potionName,
                 potionDescription,potionCategory,potionPrice,potionStock,potionImage)
         )
     }
        return potionList
    }

    override fun updatePotion(potion: Potion): Boolean {
        val args = arrayOf(potion.potionId.toString())
        val potionUpdateContent = ContentValues( )
        potionUpdateContent.put("${DatabaseHelper.ITEM_NAME}",potion.potionName)
        potionUpdateContent.put("${DatabaseHelper.ITEM_DESCRIPTION}",potion.potionDescription)
        potionUpdateContent.put("${DatabaseHelper.ITEM_CATEGORY}",potion.category)
        potionUpdateContent.put("${DatabaseHelper.ITEM_PRICE}",potion.potionPrice)
        potionUpdateContent.put("${DatabaseHelper.ITEM_STOCK}",potion.stockQuantity)
        potionUpdateContent.put("${DatabaseHelper.IMAGE_URL}",potion.potionImage)
        try {
            potionWrite.update(DatabaseHelper.SHOP_TABLE,
                potionUpdateContent,"${DatabaseHelper.ITEM_ID}=?",args)
            Log.i("PotionDAO", "updatePotion: Success on update item info")
        }catch (e:Exception){
            e.printStackTrace()
            Log.i("PotionDAO", "updatePotion: ${e.message}")
            return false
        }
        return true
    }

    override fun removePotion(potionId: Int): Boolean {
            val args = arrayOf(potionId.toString())
            try {
                potionWrite.delete(DatabaseHelper.SHOP_TABLE,
                    "${DatabaseHelper.ITEM_ID}=?",args)
                Log.i("PotionDAO", "removePotion: Success on deleting the item")

            }catch (e:Exception){
                e.printStackTrace()
                Log.i("PotionDAO", "removePotion: ${e.message} ")
                return false
            }
        return true
    }
}