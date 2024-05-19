package com.example.zappos.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.zappos.R
import com.example.zappos.databinding.ActivityAddProductsBinding
import com.example.zappos.db.ProductDatabase
import com.example.zappos.db.ProductEntity
import com.example.zappos.db.Repo

class AddProductsActivity : AppCompatActivity() {

    private lateinit var viewModel: AddViewModel
    private lateinit var binding:ActivityAddProductsBinding
    private var pName:String?=null
    private var pPrice:Double?=null
    private  lateinit var  repo:Repo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_products)
        setContentView(binding.root)
        val dao= ProductDatabase.getDatabase(this).getDao()
        viewModel= ViewModelProvider(this, AddViewModel.MyAddViewModelFactory(Repo(dao))).get(AddViewModel::class.java)
        setSupportActionBar(binding.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        binding.enterBtn.setOnClickListener{
            saveProduct()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveProduct() {
        pName=binding.etName.text?.toString()
        pPrice=binding.etPrice.text.toString().toDouble()
        if(binding.etName.text.isNullOrEmpty() || binding.etPrice.text.isNullOrEmpty()){
            Toast.makeText(this,"Empty fields",Toast.LENGTH_SHORT).show()
        }else{
            viewModel.addProduct(ProductEntity(null, pName!!,pPrice!!))
            binding.etName.text.clear();
            binding.etPrice.text.clear();

        }


    }
}