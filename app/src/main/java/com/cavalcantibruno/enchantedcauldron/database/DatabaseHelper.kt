package com.cavalcantibruno.enchantedcauldron.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context):SQLiteOpenHelper(context,DB_NAME,null,VERSION) {

    companion object {
        const val VERSION = 1
        const val DB_NAME = "enchantedCauldron.db"
        const val SHOP_TABLE = "shop_itens"
        const val ORDERS_TABLE = "cart_orders"
        const val ITEM_ID = "id"
        const val ITEM_NAME = "item_name"
        const val ITEM_DESCRIPTION = "item_description"
        const val ITEM_PRICE = "item_price"
        const val ITEM_STOCK = "stock"
        const val ITEM_CATEGORY = "category"
        const val ADVENTURER_NAME = "adventurer_name"
        const val ADVENTURER_PHONE = "adventurer_phone"
        const val CART_IDENTIFICATION ="cart_identification"
        const val ORDER_ID = "id"
        const val IMAGE_URL = "image_url"
        const val ORDER_ITEM = "item_id"
        const val ORDER_DATE = "order_date"

    }


    override fun onCreate(db: SQLiteDatabase?) {
        val sqlShop = "CREATE TABLE IF NOT EXISTS ${SHOP_TABLE} (" +
                "$ITEM_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "$ITEM_NAME TEXT, $ITEM_DESCRIPTION TEXT, $ITEM_CATEGORY TEXT, $ITEM_PRICE FLOAT," +
                "$ITEM_STOCK INTEGER,$IMAGE_URL TEXT);"
        try {
            db?.execSQL(sqlShop)
            Log.i("DatabaseHelper", "onCreate: Creating Potion Shop Database")
        }catch (e:Exception)
        {
            Log.i("DatabaseHelper", "onCreate:${e.message} ")
        }

        /*val sqlOrder = "CREATE TABLE IF NOT EXISTS ${ORDERS_TABLE} (" +
                "$ORDER_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "$ADVENTURER_NAME TEXT, $ADVENTURER_PHONE TEXT, $CART_IDENTIFICATION INTEGER," +
                "$ORDER_ITEM INTEGER, $ORDER_DATE DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY ($ORDER_ITEM) REFERENCES $SHOP_TABLE($ITEM_ID));"

        try {
            db?.execSQL(sqlOrder)
            Log.i("DatabaseHelper", "onCreate: Creating Order's Database")
        }catch (e:Exception)
        {
            Log.i("DatabaseHelper", "onCreate:${e.message} ")
        }*/
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //teste
    }
}