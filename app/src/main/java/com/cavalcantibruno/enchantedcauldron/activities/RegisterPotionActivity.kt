package com.cavalcantibruno.enchantedcauldron.activities

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import com.cavalcantibruno.enchantedcauldron.R
import com.cavalcantibruno.enchantedcauldron.database.PotionDAO
import com.cavalcantibruno.enchantedcauldron.databinding.ActivityRegisterPotionBinding
import com.cavalcantibruno.enchantedcauldron.model.Potion
import com.squareup.picasso.Picasso

class RegisterPotionActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRegisterPotionBinding.inflate(layoutInflater)
    }

    private var selectedImage: Uri? = null
    private val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var potionUpdate:Potion?=null
        val bundle = intent.extras

        generateSpinner()

        with(binding) {

            if(bundle!=null){
                potionUpdate = bundle.getSerializable("potionUpdate",Potion::class.java)
                editPotionName.setText(potionUpdate?.potionName)
                editPotionDescription.setText(potionUpdate?.potionDescription)
                //editPotionCategory.selectedItem
                editPotionPrice.setText(potionUpdate?.potionPrice.toString())
                editStock.setText(potionUpdate?.stockQuantity.toString())
                Picasso.get().load(potionUpdate?.potionImage).into(potionImageRegister)
                selectedImage = potionUpdate?.potionImage?.toUri()
            }

            potionImageRegister.setOnClickListener {
                openGallery.launch(arrayOf("image/*"))
            }

            registerItemButton.setOnClickListener {
                if(potionUpdate!=null) {
                    Log.d("RegisterPotionActivity", "RegisterButtonUpdate: ${potionUpdate} ")
                    updatePotion(potionUpdate!!)
                }
                else {
                    registerPotion()
                }

            }

        }

    }

    //Function to upload potion image from cellphone gallery
    private val openGallery = registerForActivityResult(ActivityResultContracts.OpenDocument())
    {uri ->
        if(uri!=null)
        {
            binding.potionImageRegister.setImageURI(uri)
            selectedImage = uri
            /*It's necessary to grant permission to read the URI stored in the database, to do
            this we must write this line below, the flag constant as a variable and instead of
            GetContent() on the ResultContracts we call OpenDocument(), since the late does not
            work with the takePersistableUriPermission()*/
            this.contentResolver.takePersistableUriPermission(uri, flag)
        } else
        {
            message("No image selected")
        }
    }


    //Function to update the item
    private fun updatePotion(potion:Potion){

        with(binding) {
            val pName = editPotionName.text.toString()
            val pDescription = editPotionDescription.text.toString()
            val pCategory = editPotionCategory.selectedItem.toString()
            val pPrice = editPotionPrice.text.toString()
            val pStock = editStock.text.toString()

            if(pName.isEmpty() || pDescription.isEmpty() || pCategory.isEmpty() ||
                pPrice.isEmpty() || pStock.isEmpty() || selectedImage==null){
                 message("Please fill in all fields")
            }else {

                val itemUpdate = Potion(
                    potion.potionId, pName, pDescription, pCategory, pPrice.toDouble(),
                    pStock.toInt(), selectedImage.toString()
                )
                Log.d("RegisterPotionActivity", "updatePotion: $itemUpdate ")
                val potionDAO = PotionDAO(this@RegisterPotionActivity)
                if (potionDAO.updatePotion(itemUpdate)) {
                    message("Item Updated successfully")
                }
                finish()
            }
        }

    }

    //Function to save and upload the data
    fun registerPotion() {

        with(binding) {
            val pName = editPotionName.text.toString()
            val pDescription = editPotionDescription.text.toString()
            val pCategory = editPotionCategory.selectedItem.toString()
            val pPrice = editPotionPrice.text.toString()
            val pStock = editStock.text.toString()

            if(pName.isEmpty()|| pDescription.isEmpty() || pCategory.isEmpty() ||
                pPrice.isEmpty() || pStock.isEmpty() || selectedImage == null)
            {
                message("Please, fill in all fields")
            } else {

                val item = Potion(
                    null, pName, pDescription, pCategory, pPrice.toDouble(),
                    pStock.toInt(), selectedImage.toString()
                )

                Log.d("RegisterPotionActivity", "registerPotion: ${item} ")

                val potionDAO = PotionDAO(this@RegisterPotionActivity)
                potionDAO.createPotion(item)
                finish()
            }
        }

    }

    //Auxiliary function just for a more clean code

    private fun generateSpinner(){
        val listCategory = resources.getStringArray(R.array.categories)
        binding.editPotionCategory.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,listCategory)
    }

    private fun message(text:String)
    {
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
    }


}